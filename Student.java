public class Student
{
    private int id;
    private String name;
    private double cgpa;
    private String date;
    private String gender;


    public Student()
    {
        id = 0;
        name = null;
        cgpa = 0.0;
        date = null;
        gender = null;
    }


    public void set(int id, String name, double cgpa, String date, String gender)
    {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
        this.date = date;
        this.gender = gender;
    }


    public void set(String file_line)
    {
        String[] fields = file_line.split(",");

        this.id = Integer.parseInt(fields[0]);
        this.name = fields[1];
        this.cgpa = Double.parseDouble(fields[2]);
        this.date = fields[3];
        this.gender = fields[4];
    }


    public void set(String field, String value)
    {
        switch(field)
        {
            case "id":
                this.id = Integer.parseInt(value);
                break;

            case "name":
                this.name = value;
                break;

            case "cgpa":
                this.cgpa = Double.parseDouble(value);
                break;

            case "date":
                this.date = value;
                break;

            case "gender":
                this.gender = gender;

            default:
                System.out.println("No such field. No modification took place !!!");
        }
    }


    public int std_id()
    {
        return this.id;
    }


    public String std_gender()
    {
        return this.gender;
    }


    public double std_cgpa()
    {
        return this.cgpa;
    }


    public String tabSeperated()
    {
        String tsstr = null;

        tsstr  = String.format("%4s",Integer.toString(id)) + "\t";
        tsstr += name + "\t";
        tsstr += Double.toString(cgpa) +"\t";
        tsstr += date + "\t";
        tsstr += gender;

        return tsstr;
    }


    @Override
    public String toString()
    {
        String file_line = null;

        file_line  = String.format("%-4s", Integer.toString(id)) + ",";
        file_line += String.format("%-30s", name) + ",";
        file_line += String.format("%-4s", Double.toString(cgpa)) +",";
        file_line += String.format("%-10s", date) + ",";
        file_line += String.format("%-1s", gender);

        return file_line;
    }

}
