package androcafe.visitindia.com.mydoctorspanel.model;

public class ApmtStatus {
    int id;
    int pid;
    String name;
    String age;
    String sex;
    String date_of_apmt;
    String time;
    String created_at;
    String treatment;
    String doctor_id;

    public ApmtStatus(int id, int pid, String name,String sex, String date_of_apmt, String time, String created_at, String treatment, String doctor_id) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.sex = sex;
        this.date_of_apmt = date_of_apmt;
        this.time = time;
        this.created_at = created_at;
        this.treatment = treatment;
        this.doctor_id = doctor_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate_of_apmt() {
        return date_of_apmt;
    }

    public void setDate_of_apmt(String date_of_apmt) {
        this.date_of_apmt = date_of_apmt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }
}
