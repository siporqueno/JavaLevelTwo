package Lesson_1.marathon.competitors;

public interface Competitor {
    void run(int dist, int points);

    void swim(int dist, int points);

    void jump(int height, int points);

    boolean isOnDistance();

    void info();

    int getCurrentScore();
}
