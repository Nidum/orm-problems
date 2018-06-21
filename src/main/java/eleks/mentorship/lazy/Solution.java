package eleks.mentorship.lazy;

import eleks.mentorship.db.DBInitializer;
import eleks.mentorship.entity.Course;
import eleks.mentorship.entity.Teacher;
import org.hibernate.Session;

import javax.persistence.Query;

public class Solution {
    public static void main(String[] args) {
        DBInitializer initializer = new DBInitializer();
        Session session = initializer.getSessionFactory().openSession();
        // Fetch eagerly.
        Query query = session.createQuery("From Teacher t " +
                "left join fetch t.courses " +
                "where t.id =:id");
        query.setParameter("id", 1);
        Teacher teacher = (Teacher) query.getSingleResult();

        // Start another thread which tries to access collection of courses.
        new Thread(() -> {
            // Simulate some work.
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(teacher.getName() + " courses:");
            for (Course course : teacher.getCourses()) {
                System.out.println(course);
            }
        }).start();

        session.close();
        initializer.shutdown();
    }
}
