package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Employee")
public class EmployeeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empID;
    private String empNRIC;
    private String empPassword;
    private String empFirstName;
    private String empLastName;
    private String empRace;
    private String empNationality;
    private String empDOB;
    private String empGender;
    private String empResidentAddress;
    private String empPostalCode;
    private String empContactNum;
    private String empEmail;
    private String empJobTitle;
    private String empDepartment;
    
    /* GETTER METHODS */
    public Long getEmpID() { return empID; }
    public String getEmpNRIC() { return empNRIC; }
    public String getEmpPassword() { return empPassword; }
    public String getEmpFirstName() { return empFirstName; }
    public String getEmpLastName() { return empLastName; }
    public String getEmpRace() { return empRace; }
    public String getEmpNationality() { return empNationality; }
    public String getEmpDOB() { return empDOB; }
    public String getEmpGender() { return empGender; }
    public String getEmpResidentAddress() { return empResidentAddress; }
    public String getEmpPostalCode() { return empPostalCode; }
    public String getEmpContactNum() { return empContactNum; }
    public String getEmpEmail() { return empEmail; }
    public String getEmpJobTitle() { return empJobTitle; }
    public String getEmpDepartment() { return empDepartment; }
    
    /* SETTER METHODS */
    public void setEmpID(Long empID) { this.empID = empID; }
    public void setEmpNRIC(String empNRIC) { this.empNRIC = empNRIC; }
    public void setEmpPassword(String empPassword) { this.empPassword = empPassword; }
    public void setEmpFirstName(String empFirstName) { this.empFirstName = empFirstName; }
    public void setEmpLastName(String empLastName) { this.empLastName = empLastName; }
    public void setEmpRace(String empRace) { this.empRace = empRace; }
    public void setEmpNationality(String empNationality) { this.empNationality = empNationality; }
    public void setEmpDOB(String empDOB) { this.empDOB = empDOB;}
    public void setEmpGender(String empGender) { this.empGender = empGender; }
    public void setEmpResidentAddress(String empResidentAddress) { this.empResidentAddress = empResidentAddress; }
    public void setEmpPostalCode(String empPostalCode) { this.empPostalCode = empPostalCode; }
    public void setEmpContactNum(String empContactNum) { this.empContactNum = empContactNum; }
    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }
    public void setEmpJobTitle(String empJobTitle) { this.empJobTitle = empJobTitle; }
    public void setEmpDepartment(String empDepartment) { this.empDepartment = empDepartment; }
}
