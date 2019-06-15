package Lesson_1.marathon.competitors;

public class Human implements Competitor {
    String name;

    int maxRunDistance;
    int maxSwimDistance;
    int maxJumpHeight;
    int score;

    boolean active;

    public Human(String name, int maxRunDistance, int maxSwimDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.active = true;
    }

    public Human(String name) {
        this(name, 400, 15, 3);
    }

    @Override
    public void run(int dist, int points) {
        if (dist <= maxRunDistance) {
            System.out.println(name + " успешно пробежал ");
            score += points;
        } else {
            System.out.println(name + " не смог пробежать ");
            active = false;
        }
    }

    @Override
    public void swim(int dist, int points) {
        if (dist <= maxSwimDistance) {
            System.out.println(name + " успешно проплыл ");
            score += points;
        } else {
            System.out.println(name + " не смог проплыть ");
            active = false;
        }
    }

    @Override
    public void jump(int height, int points) {
        if (height <= maxJumpHeight) {
            System.out.println(name + " успешно прыгнул ");
            score += points;
        } else {
            System.out.println(name + " не смог перепрыгнуть ");
            active = false;
        }
    }

    @Override
    public boolean isOnDistance() {
        return active;
    }

    @Override
    public void info() {
        System.out.println(name + " " + active);
    }

    @Override
    public int getCurrentScore() {
        return score;
    }
}
