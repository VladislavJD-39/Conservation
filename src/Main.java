import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        GameProgress save1 = new GameProgress(100, 5, 45, 78.1);
        GameProgress save2 = new GameProgress(17, 7, 54, 100);
        GameProgress save3 = new GameProgress(41, 2, 12, 45.78);

        saveGame("C:/Users/Пользователь/Desktop/Games/savegames/save1.dat", save1);
        saveGame("C:/Users/Пользователь/Desktop/Games/savegames/save2.dat", save2);
        saveGame("C:/Users/Пользователь/Desktop/Games/savegames/save3.dat", save3);

        ArrayList<String> saveList = new ArrayList<>();
        saveList.add("save1.dat");
        saveList.add("save2.dat");
        saveList.add("save3.dat");
        zipFiles(saveList);

        try {
            Files.delete(Path.of("C:/Users/Пользователь/Desktop/Games/savegames/save1.dat"));
            Files.delete(Path.of("C:/Users/Пользователь/Desktop/Games/savegames/save2.dat"));
            Files.delete(Path.of("C:/Users/Пользователь/Desktop/Games/savegames/save3.dat"));
            System.out.println("Файлы *.dat удалены");
        } catch (IOException x) {
            System.err.println(x);
        }


    }


    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(List<String> saveList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:/Users/Пользователь/Desktop/Games/savegames/сonservations.zip"))) {
            for (String arr : saveList) {
                FileInputStream fis = new FileInputStream("C:/Users/Пользователь/Desktop/Games/savegames/" + arr);
                ZipEntry entry1 = new ZipEntry((arr));
                zout.putNextEntry(entry1);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

    }

}
