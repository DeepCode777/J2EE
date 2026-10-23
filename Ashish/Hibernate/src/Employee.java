


public class Employee{

   
    private int id;

    
    private String fullName;

    private String department;

    // 1. Mandatory no-arg constructor
    public Employee() {}

    // Parameterized constructor for application use
    public Employee(String fullName, String department) {
        this.fullName = fullName;
        this.department = department;
    }

    // 2. Standard Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
