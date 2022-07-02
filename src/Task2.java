import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public static void main(String[] args) throws IOException {
        /*
2. Программа на вход получает путь к папке (задается через консоль).+
В заданной папке находятся текстовые файлы (формат тхт). +
Каждый файл содержит произвольный текст.+
В этом тексте может быть номер документа(один или несколько), емейл и номер телефона.+
номер документа в формате: хxхх-ууу-хххх-ууу-хуху, где х - это любая цифра,+
а у - это любая буква русского или латинского алфавита, номер телефона в формате: +(XX)XXXXXXX +
Документ может содержать не всю информацию, т.е. например, может не содержать номер телефона, или другое поле.+
Необходимо извлечь информацию из N текстовых документов. +
Число документов для обработки N задается с консоли.+
Если в папке содержится меньше документов, чем заданное число - следует обрабатывать все документы. +
Извлеченную информацию необходимо сохранить в следующую структуру данных:
Map<String, Document>, где ключ типа String - это имя документа без расширения, +
значение типа Document - объект кастомного класса, поля которого
содержат извлеченную из текстового документа информацию
Учесть вывод сообщений на случаи если,
- на вход передан путь к папке, в которой нет файлов
- все файлы имеют неподходящий формат (спедует обрабатывать только тхт файлы)
- так же сообщения на случай других исключительных ситуаций
В конце работы программы следует вывести сообщение о том, сколько
документов обработано и сколько было документов невалидного формата.
         */
        Scanner sc = new Scanner(System.in);

        File folder = new File("folderWithDoc");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (folder.isDirectory()) {
            System.out.println("dir");
        }
        if (folder.isFile()) {
            System.out.println("file");
        }

        File[] listOfFiles = folder.listFiles();//выводит имена файлов
        if (listOfFiles != null) {
            int count = 0;
            int limit = sc.nextInt();
            Map<String, Document> docMap = new HashMap<>();
            for (File file : listOfFiles) {
                if (count == limit) {
                    break;
                }
                if (file.isFile()) {
                    System.out.println(file.getName());
                }
                if (file.canRead()) {
                    System.out.println("this file can be read");
                }
                if (!checkExtension(file)) {
                    System.out.println("file has wrong extension");
                }

                ArrayList<String> docNames = new ArrayList<>();
                ArrayList<String> mails = new ArrayList<>();
                ArrayList<String> phoneNums = new ArrayList<>();
                Document document = new Document(docNames, mails, phoneNums);

                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (checkInfo("(\\d{4}[-{3}][a-zA-Zа-яА-Я]{3}-)+(\\d[a-zA-Zа-яА-Я]){2}", line)) {
                        docNames.add(line);
                    } else if (checkInfo("(^[A-Za-z\\d]{3,10}@)+[A-Za-z\\d]{1,10}(.+)", line)) {
                        mails.add(line);
                    } else if (checkInfo("\\W+([(29|44|25|33)+\\D])+(\\d{7})", line)) {
                        phoneNums.add(line);
                    }
                }
                docMap.put(file.getName().replaceAll(".txt", ""), document);
                count++;
                bufferedReader.close();
                fileReader.close();
            }
            System.out.println(docMap);
        }
    }

    public static boolean checkExtension(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".txt");
    }


    private static Boolean checkInfo(String regex, String line) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }
}







