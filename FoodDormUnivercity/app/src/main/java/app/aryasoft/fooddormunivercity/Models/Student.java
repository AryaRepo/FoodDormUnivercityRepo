package app.aryasoft.fooddormunivercity.Models;

public class Student
{

    public int StudentId;
    public String StudentName;
    public String StudentFamily;
    public String StudentCode;
    public String StudentFoodName;
    public int ReserveState;

    public Student()
    {

    }

    public Student(int studentId, String studentName, String studentFamily, String studentCode, String studentFoodName)
    {
        this.StudentId = studentId;
        this.StudentName = studentName;
        this.StudentFamily =studentFamily;
        this.StudentCode = studentCode;
        this.StudentFoodName=studentFoodName;
    }


}
