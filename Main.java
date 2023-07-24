import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int menuAction = 0;
        int foodType;
        while (menuAction != 5){

            System.out.println("Mit szeretne végrehajtani?\n" +
                    "1 - Ételek kilistázása\n"+
                    "2 - Új étel felvétele\n" +
                    "3 - Étel törlése\n" +
                    "4 - Étel módosítása\n" +
                    "5 - Program befejezése\n");
            menuAction = sc.nextInt();


            switch (menuAction){
                case 1: Pizza.Listing(); Hamburger.Listing(); break;
                case 2: Etel.Add(); break;
                case 3:
                    System.out.println("Milyen ételt szeretne törölni?\n"+
                            "1 - Pizza\n" +
                            "2 - Hamburger\n");
                    foodType = sc.nextInt();
                    if(foodType == 1) Pizza.Delete();
                    else if(foodType == 2) Hamburger.Delete();
                    else System.out.println("Hibás válasz!");
                    break;
                case 4:
                    System.out.println("Milyen ételt szeretne módosítani?\n"+
                            "1 - Pizza\n" +
                            "2 - Hamburger\n");
                    foodType = sc.nextInt();
                    if(foodType == 1) Pizza.Update();
                    else if(foodType == 2) Hamburger.Update();
                    else System.out.println("Hibás válasz!");
                    break;
            }
        }
    }




}