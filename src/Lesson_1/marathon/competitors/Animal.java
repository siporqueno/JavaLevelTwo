package Lesson_1.marathon.competitors;

public class Animal implements Competitor {
    String type;
    String name;

    int maxRunDistance;
    int maxSwimDistance;
    int maxJumpHeight;
    private int score;

    boolean onDistance;

    public Animal(String type, String name, int maxRunDistance, int maxSwimDistance, int maxJumpHeight) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.onDistance = true;
    }

    @Override
    public void run(int dist, int points) {
        if (dist <= maxRunDistance) {
            System.out.println(type + " " + name + " успешно пробежал ");
            score+=points;
        } else {
            System.out.println(type + " " + name + " не смог пробежать ");
            onDistance = false;
        }
    }

    @Override
    public void swim(int dist, int points) {
        if (dist <= maxSwimDistance) {
            System.out.println(type + " " + name + " успешно проплыл ");
            score+=points;
        } else {
            System.out.println(type + " " + name + " не смог проплыть ");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height, int points) {
        if (height <= maxJumpHeight) {
            System.out.println(type + " " + name + " успешно прыгнул ");
            score+=points;
        } else {
            System.out.println(type + " " + name + " не смог перепрыгнуть ");
            onDistance = false;
        }
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void info() {
        System.out.println(type + " " + name + " "+ onDistance);
    }

    @Override
    public int getCurrentScore() {
        return score;
    }
}
