import java.io.*;

public class StudentFile
{
    private File file_acc = null;


    public StudentFile(String filepath)
    {
        file_acc = new File(filepath);
    }


    void display()
    {
        OptimizedRandomAccessFile student_file = null;
        Student current_std = new Student();
        String current_line = null;

        try
        {
            student_file = new OptimizedRandomAccessFile(file_acc, "r");

                try {
                    while(true)
                    {
                        current_line = student_file.readLine();
                        current_std.set(current_line);
                        System.out.println(current_std.tabSeperated());
                    }
                }
                catch (EOFException e)
                {
                }
                catch (NullPointerException e)
                {
                }
        }

        catch (FileNotFoundException e)
        {
            System.out.print("The file " + file_acc.getPath() + " not found !!!");
            System.exit(1);
        } catch (IOException e)
        {
            System.out.print("The file " + file_acc.getPath() + " passed the EOF !!!");
        } finally
        {
            try{
                student_file.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    void display_alt() throws IOException
    {
        Student current_std = new Student();
        String current_line = null;

        OptimizedRandomAccessFile student_file = new OptimizedRandomAccessFile(file_acc, "r");

        while(student_file.getFilePointer() < student_file.length())
        {
            current_line = student_file.readLine();
            current_std.set(current_line);
            System.out.println(current_std);
        }
        student_file.close();
    }


    void modify(int std_id, String field, String value) throws IOException
    {
        Student current_student = new Student();
        String current_line = null;
        long current_pointer;

        OptimizedRandomAccessFile student_file = new OptimizedRandomAccessFile(file_acc, "rw");

        while(student_file.getFilePointer() < student_file.length())
        {
            current_line = student_file.readLine();
            current_student.set(current_line);

            if(std_id == current_student.std_id())
                break;
        }

        current_student.set(field, value);
        current_pointer = student_file.getFilePointer();
        student_file.seek(current_pointer - 55);
        student_file.write(current_student.toString().getBytes("UTF-8"));

        student_file.close();
    }


    void delete(int std_id) throws IOException
    {
        String current_line = null;

        FileReader student_file = new FileReader(file_acc);
        BufferedReader read_student = new BufferedReader(student_file);

        File tmpfl = new File(file_acc.getParent()+"\\temp.txt");

        FileWriter temp_file = new FileWriter(tmpfl);
        PrintWriter write_student = new PrintWriter(temp_file);

        while( (current_line = read_student.readLine()) != null)
            if(current_line.contains(Integer.toString(std_id)))
                continue;
            else
                write_student.println(current_line);


        student_file.close();
        read_student.close();

        temp_file.close();
        write_student.close();

        file_acc.delete();
        tmpfl.renameTo(file_acc);
    }


    void insert(int id, String name, double cgpa, String date, String gender) throws IOException
    {
        Student new_std = new Student();
        new_std.set(id,name,cgpa,date,gender);

        FileWriter student_file = new FileWriter(file_acc, true); // remember append = true
        PrintWriter write_student = new PrintWriter(student_file);

        write_student.println(new_std.toString());

        student_file.close();
        write_student.close();
    }


    void stats() throws IOException
    {
        double cgpa_avg, sum = 0;
        int females = 0, males = 0, total = 0;
        String cur_line = null;

        Student cur_std = new Student();

        FileReader std_read = new FileReader(file_acc);
        BufferedReader read_file = new BufferedReader(std_read);

        while( (cur_line = read_file.readLine()) != null)
        {
            cur_std.set(cur_line);

            total++;

            if(cur_std.std_gender().equals("M"))
                males++;

            if(cur_std.std_gender().equals("F"))
                females++;

            sum += cur_std.std_cgpa();
        }

        cgpa_avg = sum / total;

        std_read.close();
        read_file.close();

        File stat = new File(file_acc.getParent()+"\\stats.txt");

        FileWriter wr_stat = new FileWriter(stat);
        PrintWriter stats = new PrintWriter(wr_stat);

        stats.printf("Number of Male Students   : %d\n",males);
        stats.printf("Number of Female Students : %d\n",females);
        stats.printf("Number of Total Students  : %d\n",total);
        stats.printf("Average CGPA              : %4.2f\n",cgpa_avg);

        wr_stat.close();
        stats.close();
    }
}
