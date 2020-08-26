import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException {

        StudentFile s = new StudentFile("D:\\test\\students.txt");

        s.display_alt();
        System.out.println();

        s.modify(6221, "cgpa", "2.12");
        s.modify(6235, "date", "15-02-2001");
        s.delete(3451);
        s.insert(1050, "George Williams", 3.33, "23-03-2020", "M");
        s.display();

        s.stats();
    }
}
