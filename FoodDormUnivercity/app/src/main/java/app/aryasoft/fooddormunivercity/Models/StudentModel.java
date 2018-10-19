package app.aryasoft.fooddormunivercity.Models;

public class StudentModel
{

    public int StudentId;
    public String StudentName;
    public String StudentFamily;
    public String StudentCode;
    public String StudentFoodName;
    public boolean ReserveState;

    public StudentModel()
    {

    }

    public StudentModel(int StudentId, String StudentName, String StudentFamily, String StudentCode,String StudentFoodName)
    {
        this.StudentId = StudentId;
        this.StudentName = StudentName;
        this.StudentFamily = StudentFamily;
        this.StudentCode = StudentCode;
        this.StudentFoodName=StudentFoodName;
    }


}
