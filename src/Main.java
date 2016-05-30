import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static int maxTotalHeight = 0;
    private static int waterVolume = 0;
    private static int allVolumes = 0;
    private static List<Tank> tanks = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        String s;
        Scanner sc = new Scanner(new File("input.txt"));

        int [] tankValues = new int [100];
        int iter = 0;
        while(sc.hasNextInt())
        {
            tankValues[iter++] = sc.nextInt();
        }

        for (int i = 0; i < tankValues[0]; ++i) {
            Tank tank = new Tank();
            tank.setGroundHeight(tankValues[4*i + 1]);
            tank.setHeight(tankValues[4*i + 2]);
            tank.setWidth(tankValues[4*i + 3]);
            tank.setLength(tankValues[4*i + 4]);
            tanks.add(tank);
        }

        waterVolume = tankValues[iter - 1];

        for (Tank tank : tanks) {
            allVolumes+= tank.calcVolume();
            if(tank.getTotalHeight() > maxTotalHeight) {
                maxTotalHeight = tank.getTotalHeight();
            }
        }

        if(allVolumes < waterVolume)
            System.out.println("Overflow");
        else if(allVolumes == waterVolume)
            System.out.println(String.format("%02d", maxTotalHeight));
        else if(allVolumes > waterVolume) {
            System.out.println(String.format("%02d", bSearch(maxTotalHeight)));

        }
    }

    public static int bSearch(int offset){
        int low = 0;
        int high = offset - 1;
        int allLoadedVolumes = 0;

         while(high >= low) {
             int middle = (low + high) / 2;

             for (Tank tank : tanks) {
                 allLoadedVolumes+= tank.calcLoadedVolume(middle);
             }
             if(allLoadedVolumes == waterVolume) {
                     return middle;
                 }
             else if(allLoadedVolumes < waterVolume) {
                     low = middle + 1;
                 }
             else if(allLoadedVolumes > waterVolume) {
                     high = middle - 1;
                 }
             allLoadedVolumes = 0;
            }
        return -1;
     }
}

