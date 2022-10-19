package com.chaojilaji.meedudata.file;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 主要是文件读取操作
 */
public class FileUtils {

    public static Boolean checkFileExist(String fileName,Boolean absolutePath){
        if (absolutePath) {
            try {
                File file = new File(new File(".").getCanonicalPath() + "/" + fileName);
                return file.exists();
            } catch (IOException e) {
                return false;
            }
        }else {
            File file = new File(fileName);
            return file.exists();
        }
    }

    public static String getFileContent(File file) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static List<String> readLines(File file,int limit) {
        List<String> ans = new ArrayList<>();
        int cnt = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                ans.add(line);
                cnt++;
                if (limit != -1 && cnt >= limit){
                    break;
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }


    public static String getFileContent(String fileName, Boolean absolutePath) {
        if (absolutePath) {
            try {
                return getFileContent(new File(new File(".").getCanonicalPath() + "/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        } else {
            try {
                return getFileContent(new File(fileName));
            }catch (Exception e){
                e.printStackTrace();
            }
            return "";
        }
    }

    public static String getFileContent2(String fileName, Boolean absolutePath) {
        if (!absolutePath) {
            try {
                return getFileContent(new File(new File(".").getCanonicalPath() + "/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        } else {
            try {
                return getFileContent(new File(fileName));
            }catch (Exception e){
                e.printStackTrace();
            }
            return "";
        }
    }

    public static List<String> readlines(String fileName, Boolean absolutePath,int limit) {
        if (!absolutePath) {
            try {
                return readLines(new File(new File(".").getCanonicalPath() + "/" + fileName),limit);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        } else {
            return readLines(new File(fileName),limit);
        }
    }















    // todo 写入文件
    public static void createFile(String folder, String fileName, String value) {
        fileName = folder + "/" + fileName;
        try {
            createFileRecursion(fileName, 0);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, false));
            bufferedOutputStream.write(value.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void createFileRecursion(String fileName, Integer height) throws IOException {
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            // TODO: 2021/11/13 如果文件存在
            return;
        }
        if (Files.exists(path.getParent())) {
            // TODO: 2021/11/13 如果父级文件存在，直接创建文件
            if (height == 0) {
                Files.createFile(path);
            } else {
                Files.createDirectory(path);
            }
        } else {
            createFileRecursion(path.getParent().toString(), height + 1);
            // TODO: 2021/11/13 这一步能保证path的父级一定存在了，现在需要把自己也建一下
            createFileRecursion(fileName, height);
        }
    }


    public static void createFileWithRelativePath(String folder, String fileName, String value) {
        File directory = new File(".");
        try {
            fileName = directory.getCanonicalPath() + "/" + folder + "/" + fileName;
            createFileRecursion(fileName, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, false));
            bufferedOutputStream.write(value.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFileWithFullPath(String fileName, String value) {
        try {
            createFileRecursion(fileName, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, false));
            bufferedOutputStream.write(value.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendFileWithRelativePath(String folder, String fileName, String value) {
        File directory = new File(".");
        try {
            fileName = directory.getCanonicalPath() + "/" + folder + "/" + fileName;
            createFileRecursion(fileName, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, true));
            bufferedOutputStream.write(value.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void appendFileWithRelativePath(String folder, String fileName, String value,Boolean absolute) {
        File directory = new File(".");
        if (!absolute){
            try {
                fileName = directory.getCanonicalPath() + "/" + folder + "/" + fileName;
                createFileRecursion(fileName, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                fileName = folder + "/" + fileName;
                createFileRecursion(fileName, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, true));
            bufferedOutputStream.write(value.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
