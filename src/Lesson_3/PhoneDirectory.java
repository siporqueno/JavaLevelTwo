package Lesson_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneDirectory {

    private static Map<String, ArrayList> phoneDirectory = new HashMap<>();

    public static void main(String[] args) {
        add("Ivanov", 123);
        add("Ivanov", 123);
        add("Petrov", 456);
        add("Petrov", 456);
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
}
