package eleks.mentorship.nplusone;

import eleks.mentorship.db.DBInitializer;
import eleks.mentorship.entity.Course;
import eleks.mentorship.entity.Teacher;
import org.hibernate.Session;

import java.util.List;

public class Problem {
    public static void main(String[] args) {
        DBInitializer initializer = new DBInitializer();
        Session session = initializer.getSessionFactory().openSession();
        List<Teacher> teachers = session.createQuery("from Teacher").list();
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getName() + " courses:");
            for (Course course : teacher.getCourses()) {
                System.out.println("\t\t" + course);
            }
        }
        initializer.shutdown();
    }
}
