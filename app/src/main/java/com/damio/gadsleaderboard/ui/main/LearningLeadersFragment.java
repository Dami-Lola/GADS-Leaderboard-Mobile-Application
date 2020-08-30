package com.damio.gadsleaderboard.ui.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.damio.gadsleaderboard.learningleaders.LearningLeadersEntity;
import com.damio.gadsleaderboard.learningleaders.LearningLeadersRecyclerAdapter;
import com.damio.gadsleaderboard.R;
import com.damio.gadsleaderboard.domain.RetrofitTopLeadersInstance;
import com.damio.gadsleaderboard.domain.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class LearningLeadersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public LearningLeadersFragment() {
    }

    ConstraintLayout mainLayout;
    RecyclerView recyclerView;
    LearningLeadersRecyclerAdapter learningLeadersRecyclerAdapter;
    List<LearningLeadersEntity> learningLeadersEntityList;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_skilliqleaders, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);

        getLearningListData();
        return root;
    }

    private void getLearningListData() {

        ConnectivityManager connMgr =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading List");
        progressDialog.show();

        if (networkInfo != null && networkInfo.isConnected()) {
            RetrofitInterface retrofitInterface = RetrofitTopLeadersInstance.getRetrofitInstance().create(RetrofitInterface.class);
            Call<List<LearningLeadersEntity>> learningLeadersList = retrofitInterface.getLearningLeaders();
            learningLeadersList.enqueue(new Callback<List<LearningLeadersEntity>>() {
                @Override
                public void onResponse(Call<List<LearningLeadersEntity>> call, Response<List<LearningLeadersEntity>> response) {

                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        learningLeadersEntityList = response.body();
                        setDataInRecyclerView(learningLeadersEntityList);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Unable return the list of Skills IQ Leaders...Please try later!", Toast.LENGTH_LONG).show();
                    }
                }


                @Override
                public void onFailure(Call<List<LearningLeadersEntity>> call, Throwable t) {
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


    private void setDataInRecyclerView(List<LearningLeadersEntity> learningLeadersEntityList) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        learningLeadersRecyclerAdapter = new LearningLeadersRecyclerAdapter(learningLeadersEntityList,getActivity());
        recyclerView.setAdapter(learningLeadersRecyclerAdapter);

    }

    @Override
    public void onRefresh() {
        getLearningListData();
    }
}