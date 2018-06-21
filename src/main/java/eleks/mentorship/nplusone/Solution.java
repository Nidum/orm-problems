package eleks.mentorship.nplusone;

import eleks.mentorship.db.DBInitializer;
import eleks.mentorship.entity.Course;
import eleks.mentorship.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        DBInitializer initializer = new DBInitializer();
        Session session = initializer.getSessionFactory().openSession();
        Query query = session.createQuery("from Teacher t join fetch t.courses");
        Set<Teacher> teachers = new HashSet<>(query.list());

        for (Teacher teacher : teachers) {
            System.out.println(teacher.getName() + " courses:");
            for (Course course : teacher.getCourses()) {
                System.out.println("\t\t" + course);
            }
        }
        initializer.shutdown();
    }
}
