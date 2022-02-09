package com.company;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner fileIn; // input file connection
        PrintWriter fileOut; // HTML file connection
        String filenameIn;// original file's name ,it's what the user types in when we search for a file in their PC
        String filenameOut;// new HTML file's name( fileNameIn renamed)
        int dotIndex;// locating the position of . in filename
        String line = null;// a line from the input file

        // 1 .Ask user for a file name(or file path)
        System.out.println("Enter a file name or path");
        filenameIn = scanner.nextLine();

       // 2.Check if file exists
        try {
            //3. Rename file .txt to .HTML
            fileIn = new Scanner(new FileReader(filenameIn));
            dotIndex = filenameIn.lastIndexOf(".");// returns last index of any dots,and store'em in dotIndex
            if (dotIndex==-1){
                filenameOut = filenameIn + ".html";
            }else{
                filenameOut = filenameIn.substring(0,dotIndex) + ".html";
            }
            fileOut = new PrintWriter(filenameOut);

            // 4. Determine if file is empty (whit in try catch block)
            try {
                line = fileIn.nextLine();
            }catch (NoSuchElementException e){
                System.out.println("Error: " + e.getMessage());
            }
            if(line==null){
                System.out.println("This file is empty");
            }else {
                // 5. Read each line and insert necessary <tags>
                fileOut.println("<html>");
                fileOut.println("<head>");
                fileOut.println("</head>");
                fileOut.println("<body>");
                fileOut.println(line);

                while (fileIn.hasNextLine()){
                    fileOut.println("<br>");
                    line = fileIn.nextLine();
                    if(line.isEmpty()){
                        fileOut.println("<br>");
                    }else{
                        fileOut.println(line);
                    }
                }
                fileOut.println("</body>");
                fileOut.println("</html>");
                System.out.println("HTML File is processed");
            }
            fileIn.close();
            fileOut.close();


        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}