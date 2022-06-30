import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static HashMap<String, String> getCountryCodes() {
        HashMap<String, String> countryCodes = new HashMap<String, String>();

        try {
            File countryCodesFile = new File("coutryCodes.txt");
            Scanner fileReader = new Scanner(countryCodesFile);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                String[] countryCode = data.split("-");
                countryCodes.put(countryCode[1], countryCode[0]);

            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Country Code file not found");
            e.printStackTrace();
        }

        return countryCodes;
    }

    public static boolean shortNumber(String phoneNumber) {

        Pattern p = Pattern.compile("^[1-9][0-9]{3,5}$");
        Matcher m = p.matcher(phoneNumber);

        return m.matches();
    }

    public static boolean longNumber(String phoneNumber) {

        Pattern p = Pattern.compile("^(\\+|00)([0-9]{1,3})(( ?[0-9]){6,14}[0-9])$");
        Matcher m = p.matcher(phoneNumber);

        int noSpaces = phoneNumber.replaceAll("\\s", "").length();

        if (m.matches()) {
            if ((noSpaces - m.group(1).length()) < 9 || (noSpaces - m.group(1).length()) > 14)
                return false;
            return true;
        }
        return false;
    }

    public static String validCountryCode(String code, HashMap<String,String> countryCodes){

        String one = countryCodes.get(code.substring(0,1));
        String two = countryCodes.get(code.substring(0,2));
        String three = countryCodes.get(code);
        if(one != null) return one;
        if(two != null) return two;
        return three;
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Incorrect number of arguments. Only one input is accepted");
            System.out.println("Correct usage is: java -jar 'your_jar_name' input.txt");
            return;
        }

        HashMap<String,String> countryCodes = getCountryCodes();
        Map<String,Integer> countryCodesCount = new HashMap<String,Integer>();

        try {
            File input = new File(args[0]);
            Scanner fileReader = new Scanner(input);
            while (fileReader.hasNextLine()) {

                String data = fileReader.nextLine();

                if(shortNumber(data)){
                    countryCodesCount.merge("Portugal",1,Integer::sum);
                }
                else if(longNumber(data)){
                    String code = null;
                    data = data.replaceAll("\\s", "");
                    if(data.charAt(0)=='+'){
                        code = validCountryCode(data.substring(1,4), countryCodes);
                    }
                    else{
                        code = validCountryCode(data.substring(2,5), countryCodes);
                    }
                    if(code != null){
                        countryCodesCount.merge(code,1,Integer::sum);
                    }
                }

            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error opening input file");
            e.printStackTrace();
        }

        countryCodesCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x->System.out.println(x.getKey()+":"+x.getValue()));

    }
}
