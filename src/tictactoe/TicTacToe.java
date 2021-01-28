//Fejezzük be a hármasamőba programot!
//
//Az elméleti anyagban található függvények nevein, paraméterezésén és visszatérési értékén ne változtassunk. A megjelenítés és a felhasználóval való kommunikáció pedig legyen olyan, mint az alábbi példában van. A többi ránk van bízva! :)
//
//Segítség a metódusok elvárt működéséhez:
//
//    getValidMove – kiírja, hogy a paraméterként kapott játékos következik, majd a szintén paraméterként kapott Scanner segítségével, bekéri a lépést (sor, oszlop) és meg is vizsgálja, hogy helyes-e. Ha nem, akkor kiírja a hibaüzenetet, majd bekéri újra. Ha helyes, akkor a beolvasott sor és oszlop számot egy egydimenziós tömb két elemeként visszaadja.
//    makeMove – mindössze annyi a teendője, hogy a megadott táblának a megadott helyére (sor, oszlop), felrakja a megadott játékos számát (berakja a tábla tömbjébe).
//    checkWinner – azt vizsgálja, hogy a paraméterként megadott táblán van-e olyan sor, oszlop, vagy átló, ahol mindhárom jel egyforma. Ha igen, visszaadja azt, ha nem, akkor adjon vissza 0-t.
//    displayWinner – a kapott értéktől függően, kiírja, hogy ki győzött, vagy, hogy döntetlen lett a játék.
//
////Amire figyeljünk még, hogy az utolsó lépésig (9) kell végigmennünk, mert az utolsó lépésben is megnyerhető a játék!
//A felhasználó által beírt adatokat vastag betűvel jelöljük.
//
//Az első játékos győzelmekor ezt írja ki:
//
//Az 1-es játékos győzött.
//
//Döntetlen esetén ezt írja ki: 
//
//Döntetlen!
//
//A második játékos győzelmekor ezt írja ki:
//
//A 2-es játékos győzött.
//
//Ha olyan mezőre teszünk, amin van már jel, vagy nemlétező mezőre próbálunk rakni, akkor a következőt írja ki:
//
//Hibás mezőszám!
//
//majd újra bekéri a lépést. Figyeljünk rá (ahogy a példában is van), hogy mindkét koordinátát kérje be és csak utána döntse el, illetve írja ki, hogy helyes-e a megadott mező.
package tictactoe;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        int[][] tabla;
        int aktualisJatekos = 1;
        int eredmeny;
        int[] lepes;
        tabla = tableInit();
        Scanner sc = new Scanner(System.in);
        do {
            drawTable(tabla);
            lepes = getValidMove(sc, tabla, aktualisJatekos);
            makeMove(tabla, lepes, aktualisJatekos);
            eredmeny = checkWinner(tabla);
            aktualisJatekos = nextPlayer(aktualisJatekos);
        } while (eredmeny == 0);
        drawTable(tabla);
        displayWinner(eredmeny);
    }

    public static int[][] tableInit() {
        return new int[3][3];
    }

    public static void drawTable(int[][] tabla) {
        System.out.println("+-+-+-+");
        System.out.println("|" + tabla[0][0] + "|" + tabla[0][1] + "|" + tabla[0][2] + "|");
        System.out.println("+-+-+-+");
        System.out.println("|" + tabla[1][0] + "|" + tabla[1][1] + "|" + tabla[1][2] + "|");
        System.out.println("+-+-+-+");
        System.out.println("|" + tabla[2][0] + "|" + tabla[2][1] + "|" + tabla[2][2] + "|");
        System.out.println("+-+-+-+");
    }

    public static int[] getValidMove(Scanner sc, int[][] tabla, int aktualisJatekos) {
        System.out.println("A(z) " + aktualisJatekos + "-es játékos következik.");
        int[] lepes = new int[2];
        do {
            do {
                System.out.println("Melyik sor?");
                lepes[0] = sc.nextInt() - 1;

                System.out.println("Melyik oszlop?");
                lepes[1] = sc.nextInt() - 1;

                if (((lepes[1] > 2 || lepes[1] < 0
                        || lepes[0] > 2 || lepes[0] < 0))
                        || (tabla[lepes[0]][lepes[1]] != 0)) {
                    System.out.println("Hibás mezőszám!");
                }
            } while (lepes[1] > 2 || lepes[1] < 0
                    || lepes[0] > 2 || lepes[0] < 0);

        } while (tabla[lepes[0]][lepes[1]] != 0);
        return lepes;
    }

    public static void makeMove(int[][] tabla, int[] lepes, int aktualisJatekos) {
        tabla[lepes[0]][lepes[1]] = aktualisJatekos;
    }

    public static int checkWinner(int[][] tabla) {
        int eredmeny = 0;
        boolean vanUres = true;
        for (int jatekos = 1; jatekos < 3; jatekos++) {
            for (int sor = 0; sor < 3; sor++) {
                if (tabla[sor][0] == jatekos
                        && tabla[sor][1] == jatekos
                        && tabla[sor][2] == jatekos) {
                    eredmeny = jatekos;
                }
            }
            for (int oszlop = 0; oszlop < 3; oszlop++) {
                if (tabla[0][oszlop] == jatekos
                        && tabla[1][oszlop] == jatekos
                        && tabla[2][oszlop] == jatekos) {
                    eredmeny = jatekos;
                }
            }
            if ((tabla[0][0] == jatekos
                    && tabla[1][1] == jatekos
                    && tabla[2][2] == jatekos)
                    || (tabla[0][2] == jatekos
                    && tabla[1][1] == jatekos
                    && tabla[2][0] == jatekos)) {
                eredmeny = jatekos;
            }
        }
        for (int s = 0; s < 3; s++) {
            for (int o = 0; o < 3; o++) {
                if (tabla[s][o] == 0) {
                    vanUres = false;
                }

            }
        }
        if (vanUres == true && eredmeny == 0) {
            eredmeny = 3;
        }
        return eredmeny;
    }

    public static int nextPlayer(int aktualisJatekos) {
        if (aktualisJatekos == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    public static void displayWinner(int eredmeny) {
        switch (eredmeny) {
            case 1:
                System.out.println("Az 1-es játékos győzött.");
                break;
            case 2:
                System.out.println("A 2-es játékos győzött.");
                break;
            case 3:
                System.out.println("Döntetlen!");
                break;
            default:
                throw new AssertionError();
        }
    }
}
