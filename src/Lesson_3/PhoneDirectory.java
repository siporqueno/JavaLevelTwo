package Lesson_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PhoneDirectory {

    private static Map<String, ArrayList> phoneDirectory = new HashMap<>();

    public static void main(String[] args) {
        add("Ivanov", 123);
        add("Ivanov", 123);
        add("Petrov", 456);
        add("Petrov", 456);
        get("Ivanov");
        get("Petrov");
        get("Sidorov");
    }

    //    The following method adds one entry (Surname and telephone number) to the directory
    static void add(String surname, Integer phoneNum) {
        ArrayList<Integer> listOfPhoneNumsForCurrentSurname = phoneDirectory.get(surname);
        if (listOfPhoneNumsForCurrentSurname == null) {
            listOfPhoneNumsForCurrentSurname = new ArrayList<Integer>();
        }

        if (!listOfPhoneNumsForCurrentSurname.contains(phoneNum)) {
            listOfPhoneNumsForCurrentSurname.add(phoneNum);
            phoneDirectory.put(surname, listOfPhoneNumsForCurrentSurname);
            System.out.printf("Запись %s %d добавлена в телефонный справочник\n", surname, phoneNum);
        } else {
            System.out.printf("Запись %s %d уже есть в телефонном справочнике\n", surname, phoneNum);
        }
    }

    static void get(String surname) {
        Set<Map.Entry<String, ArrayList>> mapAsSet = phoneDirectory.entrySet();
        boolean flagFound = false;
        for (Map.Entry<String, ArrayList> mapAsSetEntry : mapAsSet) {
            if (mapAsSetEntry.getKey().equals(surname)) {
                System.out.printf(" Телефоны по фамилии %s:\n", surname);
                System.out.println(mapAsSetEntry.getValue());
                flagFound = true;
            }
        }
        if (!flagFound) System.out.printf(" Телефонов по фамилии %s не найдено\n", surname);
    }

}
