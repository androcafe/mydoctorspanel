package androcafe.visitindia.com.mydoctorspanel.model;

public class DoctorSignUp {
    String doct_email;
    String doct_password;

    public DoctorSignUp(String doct_email, String doct_password) {
        this.doct_email = doct_email;
        this.doct_password = doct_password;
    }

    public String getDoct_email() {
        return doct_email;
    }

    public void setDoct_email(String doct_email) {
        this.doct_email = doct_email;
    }

    public String getDoct_password() {
        return doct_password;
    }

    public void setDoct_password(String doct_password) {
        this.doct_password = doct_password;
    }
}
