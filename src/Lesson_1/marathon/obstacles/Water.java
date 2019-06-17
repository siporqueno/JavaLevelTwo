package Lesson_1.marathon.obstacles;

import Lesson_1.marathon.competitors.Competitor;

public class Water extends Obstacle {
    int length, points;

    public Water(int length) {
        this.length = length;
    }

    public Water(int length, int points) {
        this.length = length;
        this.points = points;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length, points);
    }
}
