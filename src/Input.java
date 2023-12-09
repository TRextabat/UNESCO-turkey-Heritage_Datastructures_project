
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Input 
{
    
    private static String[] regions = { "Akdeniz", "Doğu Anadolu", "Ege", "Güneydoğu Anadolu", "İç Anadolu", "Karadeniz", "Marmara" };


    private static String[][] cityStrings = 
    {
        {"Antalya", "Burdur", "Isparta", "Mersin", "Adana", "Hatay", "Osmaniye", "Kahramanmaraş"},
        {
        "Malatya", "Erzincan", "Elazığ", "Tunceli", "Bingöl", "Erzurum", 
        "Muş", "Bitlis", "Şırnak", "Kars", "Ağrı", "Ardahan", "Van", "Iğdır", "Hakkari"
        },
        {"İzmir", "Aydın", "Muğla", "Manisa", "Denizli", "Uşak", "Kütahya", "Afyon"},
        {"Gaziantep", "Kilis", "Adıyaman", "Şanlıurfa", "Diyarbakır", "Mardin", "Batman", "Siirt"},
        {"Eskişehir", "Konya", "Ankara", "Çankırı", "Aksaray", "Kırıkkale", "Kırşehir", "Yozgat", "Niğde", "Nevşehir", "Kayseri", "Karaman", "Sivas"},
        {
        "Bolu", "Düzce", "Zonguldak", "Karabük", "Bartın", "Kastamonu", "Çorum", "Sinop",
        "Samsun", "Amasya", "Tokat", "Ordu", "Giresun", "Gümüşhane", "Trabzon", "Bayburt","Rize", "Artvin"
        },
        { "Çanakkale", "Balıkesir", "Edirne", "Tekirdağ", "Kırklareli","İstanbul", "Bursa", "Yalova", "Kocaeli", "Bilecik", "Sakarya"}
    };
    private static  List<List<UMalani>> heritagesList = new ArrayList<>();

    static // add inner lists to heritagesList
    {
        for (int i = 0; i < regions.length; i++)
        {
            List<UMalani> jaggedArray= new ArrayList<>();
            heritagesList.add(jaggedArray);
        }

    
    }

    public static void getInput(String filePath)
    {
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
        
            while((line= bufferedReader.readLine()) != null)
            {   

                parseStrings(line);
                
            }
            bufferedReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }

    
    private static void  parseStrings(String line)
    {
        
    
        //create the pattern
        String pattern = "(\\d+)\\. (.+?) \\(([^)]+)\\) (\\d+)"; 
        Pattern r = Pattern.compile(pattern);
        
        // create the matcher 
        Matcher matcher = r.matcher(line);

        //find matcher
        if (matcher.find())
        {
            String areaname= matcher.group(2);
            List<String> citiesName = Arrays.asList(matcher.group(3).split("-"));// for multiple cities
            int announcmentYear = Integer.parseInt(matcher.group(4));

            UMalani umAlani = new UMalani(areaname, citiesName, announcmentYear);

            for (String city:citiesName)
            {
                for(int i=0; i<cityStrings.length; i++)
                {
                    for(String j:cityStrings[i])
                    {
                        if (j.equals(city))
                        {
                            heritagesList.get(i).add(umAlani);
                        }
    
                        
                    }
                }
            }
        }

        
    }

    public static void displayHeritagesList()
    {
        for (int i=0; i < heritagesList.size(); i++)
        {
            System.out.println("Region"+regions[i]+ heritagesList.get(i).size());
            for (UMalani uMalani : heritagesList.get(i))
            {
                System.out.println(uMalani);
            }
        }

    }


}
