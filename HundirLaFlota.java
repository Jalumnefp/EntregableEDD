import java.util.Scanner;

public class HundirLaFlota{

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        /* VARIABLES */
        String nom;
        int confirm = 0, disparox, randomX, randomY, select = 0, playerpoints = 0, pcpoints = 0;
        char disparoy;
        boolean disparoxerror = false, disparoyerror = false, win = false, winplayer = false, winpc = false;
        char[][]tablero = new char[11][11];
        char[][]tableroDisparos =  new char[11][11];
        char[][]tableroPC = new char[11][11];
        int[]barcos = new int[4];

        /* INICIO */
        borrarPantalla();

        System.out.println("============================\n=    ¡HUNDIR LA FLOTA!     =\n= Proyecto de programación =\n=       DAM 2022/2023      =\n============================");
        do{
            System.out.print("Introduce tu nombre: ");nom = sc.nextLine();
            if(nom.equals("")){System.out.print("\nERROR: No se ha introducido nada.\n");}
        }while(nom.equals(""));

        /* DESARROLLO */
        borrarPantalla();
            /* GENERACIÓN DE TABLEROS */
        inicializarTablero(tablero);
        inicializarTablero(tableroPC);
        inicializarTablero(tableroDisparos);
        visualizarTablero(tablero, tableroDisparos, tableroPC);
        System.out.println("\nVALE " + nom + ", ESTE ES TU TABLERO. COMO PUEDES OBSERVAR ESTÁ VACIO. AHORA COLOCA TUS BARCOS!\n1 = VALE!\n2 = NO, HAZLO TU");
        select = sc.nextInt();sc.nextLine();
        if(select == 1){
            do{
                borrarPantalla();
                /* REINICIAR TABLERO */
                inicializarTablero(tablero);

                /* POSICIONAMIENTO DE BARCOS */
                colocarBarcosJugador(tablero, tableroDisparos, tableroPC, barcos, nom);
                visualizarTablero(tablero, tableroDisparos, tableroPC);

                System.out.println("\nCOLOCASTE TODOS LOS BARCOS!\nESTAS SATISFECHO CON LA DISTRIBUCION O QUIERES VOLVER A COLOCAR LOS BARCOS?\n1 = LO ESTOY, CONTINUEMOS\n2 = NO LO ESTOY, VOLVAMOS A EMPEZAR");
                confirm = sc.nextInt();
            }while(confirm != 1);
        }else if(select==2){
            do{
                borrarPantalla();

                /* REINICIAR TABLERO */
                inicializarTablero(tablero);

                /* POSICIONAMIENTO DE BARCOS ALEATORIO */
                colocarBarcosRandom(tablero);
                visualizarTablero(tablero, tableroDisparos, tableroPC);
                System.out.println("\nESTAS SATISFECHO CON LA DISTRIBUCION O QUIERES QUE LA CAMBIE?\n1 = LO ESTOY, CONTINUEMOS\n2 = NO LO ESTOY, VOLVAMOS A EMPEZAR");
                confirm = sc.nextInt();
            }while(confirm != 1);
        }
        colocarBarcosPC(tableroPC);

            /* IMPRIMICIÓN DE TABLEROS */
        borrarPantalla();

            /* TIROTEOS */
        do{
            System.out.println("============================\n=        SCOREBOARD        =\n=     JUGADOR = "+playerpoints+"          =\n=     PC = "+pcpoints+"               =\n============================\n\n");
            visualizarTablero(tablero, tableroDisparos, tableroPC);
                /* COORDENADAS DE TIROS JUGADOR*/

            System.out.println("\n¡¡¡Vamos a disparar!!!");
            do{
            System.out.print("Introduce la coordenada Y (A-J): ");disparoy = sc.next().charAt(0);if(disparoy>='A'&&disparoy<='J'){disparoyerror=true;}else{System.out.println("\nERROR: Las coordenadas introducidas no se encuentran en el rango especificado.");}
            }while(disparoyerror!=true);
            do{
            System.out.print("Introduce la coordenada X (0-9): ");disparox = sc.nextInt();if(disparox>=0&&disparox<=9){disparoxerror=true;}else{System.out.println("\nERROR: Las coordenadas introducidas no se encuentran en el rango especificado.");}
            }while(disparoxerror!=true);

            int disparoyRes = disparoy - 65;
            int disparoxRes = disparox + 1;

                /* COORDENADAS DE TIROS PC */

            do{
                do{
                    randomX = (int) (Math.random()*10+1);
                    randomY = (int) (Math.random()*10);
                }while(tablero[randomY][randomX]=='*');
            }while(tablero[randomY][randomX]=='T');

                /* REALIZACION DE TIROS JUGADOR */
            if(tableroPC[disparoyRes][disparoxRes] == 'B'){
                tableroDisparos[disparoyRes][disparoxRes] = 'T';
                playerpoints++;
            }else if(tableroPC[disparoyRes][disparoxRes] == '~'){
                tableroDisparos[disparoyRes][disparoxRes] = '*';
            }

                /* REALIZACION DE TIROS PC */
            if(tablero[randomY][randomX] == 'B'){
                tablero[randomY][randomX] = 'T';
                pcpoints++;
            }else if(tablero[randomY][randomX] == '~'){
                tablero[randomY][randomX] = '*';
            }

            if(playerpoints == 19){
                win = true;
                winplayer = true;
            }

            if(pcpoints == 19){
                win = true;
                winpc = true;
            }

            borrarPantalla();

        }while(win == false);

        /* CONCLUSIÓN */
        if(winplayer==true){
            System.out.println("\n\n############################################################################\n¡¡¡Enhorabuena " + nom + "!!!\n¡¡¡Todos los barcos enemigos han sido tocados y hundidos!!!\n############################################################################");
        }
        
        if(winpc==true){
            System.out.println("\n\n############################################################################\nLo sentimos " + nom + ".\nTodos tus barcos han sido tocados y hundidos.\n############################################################################");
        }
        /*borrarPantalla();*/
    }

    public static boolean disparoJugador (char[][] tableroJugador, char[][] tableroDisparosJugador, char[][] tableroPC){
        // Metodo que implementa el disparo del jugador
        return false;
    }


    public static boolean disparoPC(char[][] tableroPC, char[][] tableroDisparosPC, char[][] tableroJugador){
        // Metodo que implementa el disparo del PC
        return false;
    }

    public static void inicializarTablero(char[][] tablero){
        char lt = 'A';
        char nm = '0';
        for(int y = 0; y < tablero.length; y++){
            for(int x = 0; x < tablero[y].length; x++){
                if(y < tablero.length-1) {
                    if (x == 0) {
                        tablero[y][x] = lt;
                        lt++;
                    }else{
                        tablero[y][x] = '~';
                    }
                }else if (y== tablero.length-1){
                    if (x == 0) {
                        tablero[y][x] = ' ';
                    }else{
                        tablero[y][x] = nm;
                        nm++;
                    }
                }
            }
        }
    } /* DONE */


    public static void visualizarTablero(char[][] tablero,char[][] tableroDisparos, char[][] tableroPC){
        System.out.println("\n   Tablero Jugador\t\t\t  Tablero disparos\t\t\t  Tablero PC\n");
        for(int y = 0; y < tablero.length; y++){
            for(int x = 0; x < tablero[y].length; x++){
                System.out.print(tablero[y][x]+" ");
            }
            System.out.print("\t\t\t");
            for(int x = 0; x < tableroDisparos[y].length; x++){
                System.out.print(tableroDisparos[y][x]+" ");
            }
            System.out.print("\t\t\t");
            for(int x = 0; x < tableroPC[y].length; x++){
                System.out.print(tableroPC[y][x]+" ");
            }
            System.out.println();
        }
    } /* DONE */


    public static void borrarPantalla(){
        //Este metodo borra la terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();
    } /* DONE */


    public static int sumaCeldas(int[] unVector){
        // Este metodo suma todos los valores de un vector
        return 0;
    }


    public static void colocarBarcosJugador(char[][] tablero,char[][] tableroDisparos,char[][]tableroPC, int[] barcos, String nom) {
        Scanner sc = new Scanner(System.in);
        int cont4 = 1,cont3 = 3,cont2 = 2,cont1 = 2, longitudBarco, fila, columna, cordx;
        char orientacion, cordy;
        boolean cordAerror, cord0error, longerror, orienterror;

        do {

            visualizarTablero(tablero, tableroDisparos, tableroPC);

            /* INVENTARIO */
            System.out.println("=========INVENTARIO=========\n= Barcos de 4 casillas = " + cont4 + " =\n= Barcos de 3 casillas = " + cont3 + " =\n= Barcos de 2 casillas = " + cont2 + " =\n= Barcos de 1 casillas = " + cont1 + " =\n============================");

            do {
            do {
                
                /* LONGITUD DE BARCOS */
                do{longerror = false;
                    System.out.print("Selecciona el tamaño del barco a colocar: "); longitudBarco = sc.nextInt();

                    if(cont4 == 0 && longitudBarco == 4){
                        System.out.println("\nERROR: No tienes barcos con la longitud introducida. Revise su inventario.");
                    }else if(cont3 == 0 && longitudBarco == 3){
                        System.out.println("\nERROR: No tienes barcos con la longitud introducida. Revise su inventario.");
                    }else if(cont2 == 0 && longitudBarco == 2){
                        System.out.println("\nERROR: No tienes barcos con la longitud introducida. Revise su inventario.");
                    }else if(cont1 == 0 && longitudBarco == 1){
                        System.out.println("\nERROR: No tienes barcos con la longitud introducida. Revise su inventario.");
                    }else if(longitudBarco <= 4 && longitudBarco >= 1){
                        longerror = true;
                    }else{System.out.println("\nERROR: No tienes barcos con la longitud introducida. Revise su inventario.");}

                }while(longerror == false);

                /* COORDENADAS DE BARCOS */

                    /* ORIENTACION */

                do{orienterror = false;
                    System.out.print("Seleccione la posicion del barco a colocar [H (horizontal) o V (vertical)]: "); orientacion = sc.next().charAt(0);

                    if(orientacion == 'H'){
                        orienterror = true;
                    }else if(orientacion == 'V'){
                        orienterror = true;
                    }else{System.out.println("\nERROR: Solo puedes introducir las dos letras especificadas.");}

                }while(orienterror==false);

                    /* FILA Y */

                do{cordAerror = false;
                    System.out.print("Introduce la coordenada Y [A-J]: "); cordy = sc.next().charAt(0);

                    if(cordy >= 'A' && cordy <= 'J'){
                        cordAerror = true;
                    }else{System.out.println("\nERROR: Las coordenadas introducidas no se encuentran en el rango especificado.");}

                }while(cordAerror==false);

                    /* COLUMNA X */

                do{cord0error = false;
                    System.out.print("Introduce la coordenada X [0-9]: "); cordx = sc.nextInt();

                    if(cordx >= 0 && cordx <= 9){
                        cord0error = true;
                    }else{System.out.println("\nERROR: Las coordenadas introducidas no se encuentran en el rango especificado.");}

                }while(cord0error==false);

                fila = cordy - 65;
                columna = cordx + 1;

                /* ANOMALIAS */

                if(hayColision(tablero, longitudBarco, fila, columna, orientacion)==true){
                    System.out.println("\nERROR: Has colocado un barco en un lugar erroneo (fuera del tablero). Vuelve a introducir las coordenadas.");
                }else if(colocarBarco(tablero, longitudBarco, fila, columna, orientacion)==true){
                    System.out.println("\nERROR: Has colocado un barco en un lugar erroneo (encima de otro barco). Vuelve a introducir las coordenadas.");
                }

            }while(hayColision(tablero, longitudBarco, fila, columna, orientacion)==true);
            }while(colocarBarco(tablero, longitudBarco, fila, columna, orientacion)==true);


            /* PONEDOR DE BARCOS */

            if(orientacion =='H'){
                for(int x = 0; x < longitudBarco; x++){
                    tablero[fila][columna] = 'B';
                    columna++;
                }
            }else if(orientacion =='V'){
                for(int y = 0; y < longitudBarco; y++){
                    tablero[fila][columna] = 'B';
                    fila++;
                }
            }

            /* CONTADOR INVENTARIO */

            switch (longitudBarco) {
                case 4: cont4 -= 1;break;
                case 3: cont3 -= 1;break;
                case 2: cont2 -= 1;break;
                case 1: cont1 -= 1;break;
            }

            borrarPantalla();

        } while (cont1 + cont2 + cont3 + cont4 != 0);
    }

    public static void colocarBarcosPC(char[][] tableroPC){

        int longitudBarco, columna, fila;
        char orientacion='a';
        int barcos[] = {4, 3, 3, 3, 2, 2, 1, 1};

        for(int o = 0; o < barcos.length; o++){

            do{do{
                /* LONGITUD BARCO */
                longitudBarco = barcos[o];

                /* ORIENTACION */
                int RandomNumber01 = (int)(Math.random()*2);
            
                if(RandomNumber01 == 0){
                    orientacion = 'H';
                }else if(RandomNumber01 == 1){
                    orientacion = 'V';
                }

                /* COORDENADAS */
                fila = (int)(Math.random()*(9+1));
                columna = (int)(Math.random()*(10+1));

            }while(hayColision(tableroPC, longitudBarco, fila, columna, orientacion)==true);
            }while(colocarBarco(tableroPC, longitudBarco, fila, columna, orientacion)==true);

                /* COLOCACION */
                if(orientacion =='H'){
                    for(int x = 0; x < longitudBarco; x++){
                        tableroPC[fila][columna] = 'B';
                        columna++;
                    }
                }else if(orientacion =='V'){
                    for(int y = 0; y < longitudBarco; y++){
                        tableroPC[fila][columna] = 'B';
                        fila++;
                    }
                }
            
        }
    }

    public static void colocarBarcosRandom(char[][] tablero){

        int longitudBarco, columna, fila;
        char orientacion='a';
        int barcos[] = {4, 3, 3, 3, 2, 2, 1, 1};

        for(int o = 0; o < barcos.length; o++){

            do{do{
                /* LONGITUD BARCO */
                longitudBarco = barcos[o];

                /* ORIENTACION */
                int RandomNumber01 = (int)(Math.random()*2);
            
                if(RandomNumber01 == 0){
                    orientacion = 'H';
                }else if(RandomNumber01 == 1){
                    orientacion = 'V';
                }

                /* COORDENADAS */
                fila = (int)(Math.random()*(9+1));
                columna = (int)(Math.random()*(10+1));

            }while(hayColision(tablero, longitudBarco, fila, columna, orientacion)==true);
            }while(colocarBarco(tablero, longitudBarco, fila, columna, orientacion)==true);

            /* COLOCACION */
            if(orientacion =='H'){
                for(int x = 0; x < longitudBarco; x++){
                    tablero[fila][columna] = 'B';
                    columna++;
                }
            }else if(orientacion =='V'){
                for(int y = 0; y < longitudBarco; y++){
                    tablero[fila][columna] = 'B';
                    fila++;
                }
            }    
        }
    }


    public static boolean hayColision(char[][] tablero, int longitudBarco, int fila, int columna, char orientacion){
        boolean colision = false;

        if(orientacion=='H' && longitudBarco == 4 && columna >= 7){
            colision = true;
        }else if(orientacion=='H' && longitudBarco == 3 && columna >= 8){
            colision = true;
        }else if(orientacion=='H' && longitudBarco == 2 && columna >= 9){
            colision = true;
        }
        
        if(orientacion=='V' && longitudBarco == 4 && fila >= 7){
            colision = true;
        }else if(orientacion=='V' && longitudBarco == 3 && fila >= 8){
            colision = true;
        }else if(orientacion=='V' && longitudBarco == 2 && fila >= 9){
            colision = true;
        }
        
        return colision;
    }


    public static boolean colocarBarco(char[][] tablero, int longitudBarco, int fila, int columna, char orientacion){
        boolean colision = false;
        if(orientacion=='H'){
            for(int x = 0; x < longitudBarco; x++){
                if (tablero[fila][columna] == '~'){
                    columna++;
                }else{
                    colision = true;
                }
            }
        }else if(orientacion=='V'){
            for(int y = 0; y < longitudBarco; y++){
                if (tablero[fila][columna] == '~'){
                    fila++;
                }else{
                    colision = true;
                }
            }
        }
        return colision;
    }
    
}