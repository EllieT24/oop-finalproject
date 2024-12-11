package oopfinalproject;

import java.io.*;
import java.util.*;

public class FileManager {

    private static final String FILE_NAME = "todoList.txt";

    // Method to load ToDo lists from a file
    public static ArrayList<list> loadToDoLists() {
        ArrayList<list> todoLists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("[") && line.endsWith("]")) {
                    line = line.substring(1, line.length() - 1);
                    String[] parts = line.split(", ", 3);
                    String title = parts[0].trim();
                    String description = parts[1].trim();
                    String taskList = parts[2].trim();
                    list toDoList = new list(title, description);
                    toDoList.addTasks(Arrays.asList(taskList.split(", ")));
                    todoLists.add(toDoList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todoLists;
    }

    // Method to save ToDo lists to a file
    public static void saveToDoLists(ArrayList<list> todoLists) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (list toDoList : todoLists) {
                writer.write("[" + toDoList.getTitle() + ", " + toDoList.getDescription() + ", " + toDoList.getTasksAsString() + "]\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to append a new ToDo list to the file
    public static void saveListToFile(list newList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("[" + newList.getTitle() + ", " + newList.getDescription() + ", " + newList.getTasksAsString() + "]\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addNewList(list newList) {
        ArrayList<list> todoLists = loadToDoLists();
        todoLists.add(newList);
        saveToDoLists(todoLists);
    }
}