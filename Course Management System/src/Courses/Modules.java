package Courses;


public class Modules{

    private String moduleName;
    private String moduleCode;
    private String instructorName;
    private int semester;
    private int credits;
    private Boolean coreStatus;
    private Boolean enrolledStatus;
    private Double marksSecured;

    
    public Modules(){
        
    }

    public Modules(String moduleName, String moduleCode){
        
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.credits = 15;
        this.semester = 0;
        this.coreStatus = true;
        if(this.coreStatus == true){
            this.enrolledStatus = true;
        } else {
            this.enrolledStatus = false;
        }
        this.instructorName = null;
        this.marksSecured = 0.0;

    }

    public String getModuleName() {
        return moduleName;

    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Boolean getCoreStatus() {
        return coreStatus;
    }
    public void setCoreStatus(Boolean coreStatus) {
        this.coreStatus = coreStatus;
    }

    public Boolean getEnrolledStatus(){
        return enrolledStatus;
    }
    public void setEnrolledStatus(Boolean enrolledStatus) {
        this.enrolledStatus = enrolledStatus;
        
    }

    public String getInstructorName() {
        return instructorName;
    }
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Double getMarksSecured() {
        return marksSecured;
    }
    public void setMarksSecured(Double marksSecured) {
        this.marksSecured = marksSecured;
    }


    


}
