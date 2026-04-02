package main;

import java.io.*;

public class StatManager {
    private static final String FILE_NAME = "stat.dat";

    public void save(UserStat userStat) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(userStat);
            System.out.println("Статистика успешно сохранена в файл: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении статистики: " + e.getMessage());
        }
    }


    public UserStat load() {
        if (!statExist()) {
            System.out.println("Файл со статистикой не найден. Создаётся новая статистика.");
            return new UserStat();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            UserStat loadedStat = (UserStat) ois.readObject();
            System.out.println("Статистика успешно загружена из файла: " + FILE_NAME);
            return loadedStat;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке статистики: " + e.getMessage());
            e.printStackTrace();
            return new UserStat();
        }
    }

    public boolean statExist() {
        File file = new File(FILE_NAME);
        return file.exists() && file.length() > 0;
    }
}
