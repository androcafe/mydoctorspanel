package androcafe.visitindia.com.mydoctorspanel.model;

public class TodaysShedule {

    int id;
    int pid;
    String patient_name;
    int patient_age;
    String apmt_date;
    String age;
    String treatment;
    String time;
    String sex;
    String docid;


    public TodaysShedule(int id, int pid, String patient_name, int patient_age, String apmt_date, String age, String treatment, String time, String sex, String docid) {
        this.id = id;
        this.pid = pid;
        this.patient_age = patient_age;
        this.apmt_date = apmt_date;
        this.age = age;
        this.treatment = treatment;
        this.time = time;
        this.sex = sex;
        this.docid = docid;
        this.patient_name=patient_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }


    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public int getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(int patient_age) {
        this.patient_age = patient_age;
    }

    public String getApmt_date() {
        return apmt_date;
    }

    public void setApmt_date(String apmt_date) {
        this.apmt_date = apmt_date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }
}

