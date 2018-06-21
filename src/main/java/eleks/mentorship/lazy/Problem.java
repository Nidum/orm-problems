package eleks.mentorship.lazy;

import eleks.mentorship.db.DBInitializer;
import eleks.mentorship.entity.Course;
import eleks.mentorship.entity.Teacher;
import org.hibernate.Session;

public class Problem {
    public static void main(String[] args) {
        DBInitializer initializer = new DBInitializer();
        Session session = initializer.getSessionFactory().openSession();
        Teacher teacher = session.get(Teacher.class, 1);

        // Start another thread which tries to access collection of courses.
        new Thread(() -> {
            // Simulate some work.
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(teacher.getName() + " courses:");

            // LazyInitializationException
            for (Course course : teacher.getCourses()) {
                System.out.println(course);
            }
        }).start();

        session.close();
        initializer.shutdown();
    }
}
