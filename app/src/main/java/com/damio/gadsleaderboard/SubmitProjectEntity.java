package com.damio.gadsleaderboard;

public class SubmitProjectEntity {

    private String emailAddress;
    private String firstName;
    private String lastName;
    private String projectLink;

    public SubmitProjectEntity() {
    }

    public SubmitProjectEntity(String emailAddress, String firstName, String lastName, String projectLink) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.projectLink = projectLink;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }
}
