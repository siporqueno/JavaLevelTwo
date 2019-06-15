package Lesson_1.marathon;

import Lesson_1.marathon.competitors.Competitor;
import Lesson_1.marathon.obstacles.Obstacle;

public class Course {
    Obstacle[] course = new Obstacle[3];

    public Course(Obstacle[] course) {
        this.course = course;
    }

    public void doCourseTogether(Team team) {

        System.out.print("Список препятствий: ");
        for (Obstacle o : course) {
            System.out.print(o.getClass().getSimpleName() + " ");
        }
        System.out.println("\n\nНа старт, внимание, марш!");

        for (Competitor c : team.teamMembers) {
            for (Obstacle o : course) {
                o.doIt(c);
            }
        }
    }
}
