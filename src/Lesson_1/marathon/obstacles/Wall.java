package Lesson_1.marathon.obstacles;

import Lesson_1.marathon.competitors.Competitor;

public class Wall extends Obstacle {
    int height, points;

    public Wall(int height) {
        this.height = height;
    }

    public Wall(int height, int points) {
        this.height = height;
        this.points = points;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.jump(height, points);
    }
}
