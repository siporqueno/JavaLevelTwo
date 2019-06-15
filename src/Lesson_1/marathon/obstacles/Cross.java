package Lesson_1.marathon.obstacles;

import Lesson_1.marathon.competitors.Competitor;

public class Cross extends Obstacle{
    int length, points;

    public Cross(int length) {
        this.length = length;
    }

    public Cross(int length, int points) {
        this.length = length;
        this.points = points;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length, points);
    }
}
