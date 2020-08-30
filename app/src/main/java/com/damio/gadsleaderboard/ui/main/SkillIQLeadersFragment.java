package com.damio.gadsleaderboard.ui.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.damio.gadsleaderboard.R;
import com.damio.gadsleaderboard.domain.RetrofitTopLeadersInstance;
import com.damio.gadsleaderboard.domain.RetrofitInterface;
import com.damio.gadsleaderboard.skilliqleaders.SkillIQLeadersEntity;
import com.damio.gadsleaderboard.skilliqleaders.SkillIQLeadersRecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLeadersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public SkillIQLeadersFragment() {
    }

    RecyclerView recyclerView;
    SkillIQLeadersRecyclerAdapter skillIQLeadersRecyclerAdapter;
    List<SkillIQLeadersEntity> skillIQLeadersEntity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_learningleaders, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);

        getSkillsListData();
        return root;
    }

    private void getSkillsListData() {

        ConnectivityManager connMgr =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading List");
        progressDialog.show();

        if (networkInfo != null && networkInfo.isConnected()) {
            RetrofitInterface retrofitInterface = RetrofitTopLeadersInstance.getRetrofitInstance().create(RetrofitInterface.class);
            Call<List<SkillIQLeadersEntity>> skillIQLeadersList = retrofitInterface.getSkillIQLeaders();
            skillIQLeadersList.enqueue(new Callback<List<SkillIQLeadersEntity>>() {
                @Override
                public void onResponse(Call<List<SkillIQLeadersEntity>> call, Response<List<SkillIQLeadersEntity>> response) {

                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        skillIQLeadersEntity = response.body();
                        setDataInRecyclerView(skillIQLeadersEntity);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Unable return the list of Skills IQ Leaders...Please try later!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<SkillIQLeadersEntity>> call, Throwable t) {
                    progressDialog.dismiss();
                    Log.d("Could not return the list of Skills IQ Leaders.", t.getStackTrace().toString());
                }
            });
        }

        else {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "Please confirm you are connected to the internet", Toast.LENGTH_LONG).show();
        }

    }


    private void setDataInRecyclerView(List<SkillIQLeadersEntity> skillIQLeadersEntityList) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        skillIQLeadersRecyclerAdapter = new SkillIQLeadersRecyclerAdapter(skillIQLeadersEntityList,getActivity());
        recyclerView.setAdapter(skillIQLeadersRecyclerAdapter);

    }

    @Override
    public void onRefresh() {
        getSkillsListData();
    }
}