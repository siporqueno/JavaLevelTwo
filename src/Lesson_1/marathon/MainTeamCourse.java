package Lesson_1.marathon;

import Lesson_1.marathon.competitors.Cat;
import Lesson_1.marathon.competitors.Competitor;
import Lesson_1.marathon.competitors.Dog;
import Lesson_1.marathon.competitors.Human;
import Lesson_1.marathon.obstacles.Cross;
import Lesson_1.marathon.obstacles.Obstacle;
import Lesson_1.marathon.obstacles.Wall;
import Lesson_1.marathon.obstacles.Water;

public class MainTeamCourse {
    private static Competitor[] competitors = {new Human("Bob"), new Cat("Vaska"), new Dog("Tuzik")};
    private static Obstacle[] obstacles = {new Cross(80, 10), new Wall(2, 5), new Water(10, 15)};

    public static void main(String[] args) {
        Team dreamTeam = new Team("Команда мечты", competitors);
        Course triathlon = new Course(obstacles);
        dreamTeam.teamInfo();
        triathlon.doCourseTogether(dreamTeam);
        dreamTeam.winnersInfo();
    }
}
