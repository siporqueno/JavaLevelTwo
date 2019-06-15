package Lesson_1.marathon;

import Lesson_1.marathon.competitors.Competitor;

public class Team {
    String teamName;
    Competitor[] teamMembers = new Competitor[4];
    int teamScore;

    public Team(String teamName, Competitor[] teamMembers) {
        this.teamName = teamName;
        this.teamMembers = teamMembers;
    }

    void teamInfo() {
        System.out.println(teamName);
        for (Competitor c : teamMembers) {
            c.info();
        }
    }

    void winnersInfo() {
        System.out.println("\nСписок успешно прошедших все элементы дистанции");
        for (Competitor c : teamMembers) {
            if (c.isOnDistance()) {
                c.info();
            }
            teamScore += c.getCurrentScore();
        }
        System.out.println("Счет команды: " + teamScore);
    }
}
