/**
 * Реализовать консольное приложение - телефонный справочник.
 * У одной фамилии может быть несколько номеров.
 * Пользователь может вводить команды:
 * 1: ADD Ivanov 88005553535 - добавить в справочник новое значение. Первый аргумент - фамилия, второй - номер телефона
 * 2: GET Ivanov - получить список всех номеров по фамилии
 * 3: REMOVE Ivanov - удалить все номера по фамилии
 * 4: LIST - Посмотреть все записи
 * 5: EXIT - Завершить программу
 * <p>
 * Если при GET или REMOVE нужной фамилии нет, вывести информацию об этом.
 * <p>
 * Пример взаимодействия (=> - это вывод на консоль):
 * ADD Ivanov 8 800 555 35 35
 * ADD Ivanov 8 800 100 10 10
 * GET Ivanov => [8 800 555 35 35, 8 800 100 10 10]
 * ADD Petrov 8 555 12 34
 * LIST => Ivanov = [8 800 5553535, 8 800 100 10 10], Petrov = [8 555 12 34]
 * REMOVE Ivanov
 * LIST => Petrov = [8 555 12 34]
 * GET NoName => Не найдена запись с фамилией "NoName"
 * REMOVE NoName => Не найдена запись с фамилией "NoName"3
 */

package Lesson05;

import java.util.*;


public class PhoneBook {
    private static final Map<String, Set<String>> contacts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        label:
        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            String[] tokens = input.split(" ");
            String command = tokens[0];

            switch (command) {
                case "ADD": {
                    if (tokens.length < 3) {
                        System.out.println("Invalid ADD command parameters");
                        continue;
                    }

                    String lastName = tokens[1];
                    String phoneNumber = tokens[2];

                    addContact(lastName, phoneNumber);
                    break;
                }
                case "GET": {
                    if (tokens.length < 2) {
                        System.out.println("Invalid GET command parameters");
                        continue;
                    }

                    String lastName = tokens[1];

                    getContacts(lastName);
                    break;
                }
                case "REMOVE": {
                    if (tokens.length < 2) {
                        System.out.println("Invalid REMOVE command parameters");
                        continue;
                    }

                    String lastName = tokens[1];

                    removeContacts(lastName);
                    break;
                }
                case "LIST":
                    listContacts();
                    break;
                case "EXIT":
                    System.out.println("Good luck!");
                    break label;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }

    private static void addContact(String lastName, String phoneNumber) {
        Set<String> phoneNumbers = contacts.getOrDefault(lastName, new HashSet<>());
        phoneNumbers.add(phoneNumber);
        contacts.put(lastName, phoneNumbers);
        System.out.println("Number added successfully");
    }

    private static void getContacts(String lastName) {
        Set<String> phoneNumbers = contacts.get(lastName);
        if (phoneNumbers != null) {
            for (String phoneNumber : phoneNumbers) {
                System.out.println(phoneNumber);
            }
        } else {
            System.out.println("No contacts for last name: " + lastName);
        }
    }

    private static void removeContacts(String lastName) {
        Set<String> phoneNumbers = contacts.remove(lastName);
        if (phoneNumbers != null) {
            System.out.println("Contacts for last name: " + lastName + " successfully deleted");
        } else {
            System.out.println("No contacts for last name: " + lastName);
        }
    }

    private static void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Phone directory is empty");
        } else {
            for (String lastName : contacts.keySet()) {
                Set<String> phoneNumbers = contacts.get(lastName);
                for (String phoneNumber : phoneNumbers) {
                    System.out.println(lastName + " - " + phoneNumber);
                }
            }
        }

    }
}
