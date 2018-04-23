package com.example.fileprog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Example1 {

   private static ArrayList<Ourfiles> files = new ArrayList();
   private static String contents;

    public static void main(String[] args) throws IOException {
        boolean end=false;
        while (!end) {
            System.out.println("Введите путь к старт каталогу");
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            System.out.println("Выполняем программу по этому пути " + s);
            File dir = new File(s);

            if (dir.isDirectory()) {
                readFiles(dir);
                Collections.sort(files, new Comparator<Ourfiles>() {
                    @Override
                    public int compare(Ourfiles o1, Ourfiles o2) {
                        return compareTo(o1.getFilenames(),o2.getFilenames());
                    }
                });

                File resultFile = new File("C://servtemp//example1//result.txt");  //создаем файл с результатом, если его нету в старт каталоге
                try
                {
                    boolean created = resultFile.createNewFile();
                    if(created)
                        System.out.println("Файл создан");
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }

                try(FileWriter writer = new FileWriter("C://servtemp//example1//result.txt", false)) //запись результата программы
                {
                    for(int i = 0;i<files.size();i++){
                        writer.write(files.get(i).filecontent);
                        writer.write("\r\n"); //переход на новую строку между записями
                    }
                    writer.flush();
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }


            end=true;
            } else {
                System.out.println("Введен не каталог");
            }
        }
    }


    public static void readFiles(File baseDirectory) throws IOException {
        if (baseDirectory.isDirectory()){
            for (File file : baseDirectory.listFiles()) {
                if(file.isFile()){
                    if(getFileExtension(file).equals("txt")) {
                       contents = readUsingFiles(file.getPath());
                       files.add(new Ourfiles(file.getName(),contents));
                    }
                }else {
                    readFiles(file);
                }
            }
        }
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    private static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static int compareTo(String str1, String str2){
        return (str1.compareTo(str2));
    }
}
