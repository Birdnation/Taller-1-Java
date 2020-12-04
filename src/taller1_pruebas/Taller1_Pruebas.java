/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller1_pruebas;

import ucn.*;

/**
 * Programa desarrollado en base a los enunciados y parametros expuestos
 * en el taller 1 de programacion avanzada UCN.
 * Fecha 20/09/2017
 * @author Rodrigo Dominguez.
 * @version Taller1.
 */
public class Taller1_Pruebas {

    /**
     * Metodo Principal Inicio de programa (main).
     * @param args
     */
    public static void main(String[] args) {
               int contarRegistro = Contar("textos/VuelosRealizados.txt");      //Almacena el total de los registros.  
               String [] origenes = new String[contarRegistro];                 //Vector para almacenar todos los origenes
               String [] destinos = new String[contarRegistro];                 //Vector para almacenar todos los destinos
               int [][] pasajeros = new int[contarRegistro][contarRegistro];    //Matriz que asocia los destinos y los origenes con los pasajeros transportados
               String [] ciudades = new String[contarRegistro];                 //Vector Auxiliar para almacenar todas las ciudades
               GenerarArreglo (origenes,destinos,pasajeros);                    
               OrdenAlfabetico(origenes, destinos,ciudades);
               GenerarArchivoA(origenes,destinos,pasajeros,ciudades);
               DesplegarPantallaA(origenes,destinos,pasajeros,ciudades);
               EliminarC (origenes,destinos,pasajeros);
               GenerarArchivoB (origenes,destinos,pasajeros,ciudades);
               
    }

    /**
     * Funcion que cuenta todos los registros inculidos en la ruta "textos/VuelosRealizados.txt"
     * @param path textos/VuelosRealizados.txt
     * @return el numero de registros contabilizados.
     */
    public static int Contar(String path) {
        int contar = 0;                                                         //Inicializa el conteo en 0
        In archivo = new In ("textos/VuelosRealizados.txt");                    //lectura del archivo de entrada
        while (!archivo.isEmpty()) {
            String linea = archivo.readLine();
            contar ++;
        }
        archivo.close();
        return contar;
    }//fin Funcion

    /**
     * Metodo que vicula los 2 vectores con la matriz ingresando los datos
     * @param origenes vector para almacenar las ciudades en el campo origen
     * @param destinos vector para almacenar las ciudades en el campo destino
     * @param pasajeros matriz para almacenar la cantidad de pasajeros por origen y destino
     */
    public static void GenerarArreglo(String[] origenes, String[] destinos, int[][] pasajeros) {
        int aux = 0;                                                            //auxiliares utilizados en
        int aux2 = 0;                                                           //los calculos de este metodo
        int aux3 = 0;                                                           //aux, aux2, aux3
        In archivo = new In ("textos/VuelosRealizados.txt");
        
        //Ciclo para ingresar "0" a los vectores y matriz.
        for (int i = 0; i < origenes.length; i++) {                             
            destinos [i] = "0";
            origenes [i] = "0";
            for (int j = 0; j < pasajeros.length; j++) {
                pasajeros[i][j] = 0;
            }
        }
        //Fin Ciclo
        
        //Inicio ciclo que censa cada linea del registro
        while (!archivo.isEmpty()) {                                //Repite ciclo hasta que no quedan registros en el txt
            String linea = archivo.readLine();                                  
            String registro [] = linea.split(",");                      //Separa los campos por el caracter (,)
            
            //Algotirmo de busqueda en el vector origenes
            for (int i = 0; i < origenes.length; i++) {
                //condicion que iguala la posicion de un origen con el registro
                if (origenes[i].compareTo(registro[1]) == 0) {
                    break;
                }//fin condicion
                aux++;
            }//fin ciclo
            
            //condicion que agrega un origen de los registros que no se encuentra en el vector origen
            if (aux == origenes.length) {
                origenes [aux2] = registro[1];
                aux2++;
            }//fin condicion
            
            aux = 0;
            
            //Algoritmo de busqueda en el vector destino
            for (int i = 0; i < destinos.length; i++) {
                //condicion que iguala la posicion de un destino con el registro
                if (destinos[i].compareTo(registro[2]) == 0) {
                    break;
                }//fin condicion
                aux++;
            }//fin ciclo
            
            //condicion que agrega un destino de los registros que no se encuentra en el vector origen
            if (aux == destinos.length) {
                destinos [aux3] = registro[2];
                aux3++;
            }//fin condicion
            
            aux = 0;
            
            int auxPasajeros = Integer.parseInt(registro[5]);           //Variable que almacena el numero de pasajeros en un entero
            
            //ciclo for que busca el origen y destino y asigna el valor de los pasajeros a la matriz
            for (int i = 0; i < origenes.length; i++) {
                if (origenes[i].contains(registro[1])) {
                    for (int j = 0; j < destinos.length; j++) {
                        if (registro[2].contains(destinos[j])) {
                            pasajeros[i][j] = pasajeros[i][j] + auxPasajeros;
                        }
                    }
                }
            }//fin ciclo
        }//fin Ciclo
    }//fin Metodo

    /**
     * Metodo que ordena alfabeticamente en base a las ciudades registradas en los vectores
     * origenes y destinos y almacena dichas ciudades en un vector nuevo.
     * @param origenes Lee los origenes ya registrados
     * @param destinos Lee los destinos ya registrados
     * @param ciudades Almacena las ciudades sin tepetir y ordenadas alfabeticamente.
     */
   public static void OrdenAlfabetico(String[] origenes, String[] destinos, String[] ciudades) {
        
        int aux = 0;
        int aux2 = 0;
        
        //ciclo for para ingresar valores "0" al vector ciudades
        for (int i = 0; i < ciudades.length ; i++) {
            ciudades [i] = "0";
        }//fin ciclo for
        
        //algoritmo de busqueda en vector origenes y agregarlos al vector ciudades
        for (String i : origenes) {
            for (int j = 0; j < ciudades.length; j++) {
                if (ciudades[j].compareTo(i) == 0) {
                    break;
                }
                aux++;
            }
            if (aux == ciudades.length) {
                ciudades[aux2] = i;
                aux2++;
            }
            aux = 0;
        }//fin algoritmo
        
        //Algoritmo de busqueda en vector destinos y agregarlos al vector ciudades
        for (String i : destinos) {
            for (int j = 0; j < ciudades.length; j++) {
                if (ciudades[j].compareTo(i) == 0) {
                    break;
                }
                aux++;
            }
            if (aux == ciudades.length) {
                ciudades[aux2] = i;
                aux2++;
            }
            aux = 0;
        }//fin algoritmo
        
        //Algoritmo de ordenamiento alfabeticamente decendente
        for (int i = 0; i < ciudades.length; i++) {
            for (int j = i+1; j < ciudades.length; j++) {
                if (ciudades[j] != "0") {
                    if (ciudades[i].compareTo(ciudades[j]) > 0) {
                        String auxCiudad = ciudades[i];
                        ciudades[i] = ciudades [j];
                        ciudades[j] = auxCiudad;
                    }
                }
            }
        }//fin Algoritmo
    }//fin Metodo

   /**
    * Metodo que genera el archivo ciudades.txt
    * @param origenes Lee las ciudades registradas en el vector origen
    * @param destinos Lee las ciudades registradas en el vector destino
    * @param pasajeros Lee las cantidades de pasajeros 
    * @param ciudades Vector auxiliar que almacena la totalidad de las ciudades
    */
    public static void GenerarArchivoA(String[] origenes, String[] destinos, int[][] pasajeros, String[] ciudades) {
        Out salida = new Out ("textos/Ciudades.txt");                           //Crear un archivo en la ruta especificada
        int aux1 = 0;
        int aux2 = 0;
        boolean aux3 = true;
        boolean aux4 = true;
           
        for (int i = 0; i < ciudades.length; i++) {     //Inicio ciclo que recorre todas las pocisiones de cidades
            if (ciudades[i] != "0") {                   //Inicio condicion de ciudad valida
                for (int j = 0; j < origenes.length; j++) {             //Inicio ciclo que reccore todas las posiciones de los origenes
                    if (ciudades[i].compareTo(origenes[j]) == 0) {      //Inicio comparacion de ciudad con origen
                        salida.print("En la ciudad de: " + ciudades[i]);
                        int suma2 = 0;
                        for (int k = 0; k < origenes.length; k++) {     //Inicio ciclo que suma los pasajeros en la condicion anterior
                            suma2 += pasajeros[j][k];
                        }//Fin ciclo sima
                        aux4 = true;
                        for (int k = 0; k < destinos.length; k++) {     //inicio ciclio que recorre todas las posiciones de los destinos
                            if (origenes[j].contains(destinos[k])) {    //condicion de comparacion ciudades con destinos
                                int suma3 = 0;
                                for (int l = 0; l < 10; l++) {          //Ciclo de suma condicion anterior
                                    suma3 += pasajeros[l][k];
                                }//Fin ciclo suma
                                salida.print(", Entraron: " + suma3 + " Pasajeros.");
                                aux4 = false;
                            }//fin condicion destinos
                            
                        }//fin ciclo destinos
                        if (aux4 == true) {                             //condicion si no encuentra pasajeros en la condicion de destinos
                            salida.print(" Entraron: 0 Pasajeros.");
                        }
                        salida.println(" Y Salieron: " + suma2 + " Pasajeros.");
                        break;
                    }//Fin comparacion destinos
                    
                    if (ciudades[i].compareTo(origenes[j]) != 0 && ciudades[i].compareTo(destinos[j]) == 0) { //condicion si la ciudad solo se encuentra en 1 vector
                        salida.print("En la ciudad de: " + ciudades[i]);
                        int suma = 0;
                        for (int k = 0; k < destinos.length; k++) {     //ciclo de suma
                            suma += pasajeros[k][j];
                        }//fin ciclo
                        
                        salida.print(", Entraron: " + suma + " Pasajeros.");
                        aux3 = true;
                        for (int k = 0; k < origenes.length; k++) {
                            if (destinos[j].contains(origenes[k])) {
                                suma = 0;
                                for (int l = 0; l < origenes.length; l++) {
                                    suma += pasajeros[k][l];
                                }
                                salida.println(" Y Salieron: " + suma + " Pasajeros.");
                                aux3 = false;
                            }
                        }
                        if (aux3 == true) {
                            salida.println(" Y Salieron: 0 Pasajeros.");
                        }
                        break;
                    }                    
                    aux2++;
                }
                aux2 = 0;
            }            
        }
    }

    /**
     * Subprograma destinado a mostrar por pantalla los datos deacuerdo a un String de entrada
     * @param origenes  Vector de origenes
     * @param destinos Vector de destinos
     * @param pasajeros Matriz de pasajeros
     * @param ciudades Vector auxiliar para almacenar todas las ciudades
     */
    private static void DesplegarPantallaA(String[] origenes, String[] destinos, int[][] pasajeros, String[] ciudades) {
        StdOut.print("Ingrese la ciudad a consultar: ");
        String lecturaOrigen = StdIn.readLine();                //String de lectura por teclado
        boolean aux = true;
        
        while (aux == true) {           //ciclo while para encapsular solo ciudades validas
            for (int i = 0; i < origenes.length; i++) {     //ciclo for que recorre todas las posiciones de origenes
                if (origenes[i].compareToIgnoreCase(lecturaOrigen) == 0) {  //condicion que compara la ciudad de origen con el string de lectura
                    StdOut.println("Para la ciudad de: " + lecturaOrigen.toUpperCase() + ", Han llegado pasajeros desde:");
                    for (int j = 0; j < destinos.length; j++) {     //ciclo que recorre todas las posiciones de los destinos
                        if (pasajeros[i][j] != 0) {                 //condicion que valida solo los pasajeros > 0
                            StdOut.print("  - " + destinos[j]);     
                            StdOut.println(", Con " + pasajeros[i][j] + " Pasajeros.");
                            
                        }//Fin condicion
                        aux = false; //auxiliar para salir del while
                    }//fin ciclo destinos
                }//fin condicion de comparacion
            }//fin ciclo de origenes
            if (aux == true) {          //Condicion que compara el auxiliar
                StdOut.print("La ciudad no se a encontrado, Reintente: ");
                lecturaOrigen = StdIn.readLine();                
            }//fin condicion de auxiliar
        }//fin while
        
        //inicio problema B punto 2
        String antofa = "Antofagasta";      //String de condicion antofagasta
        int max = -1;                       //auxiliar para el maximo
        int auxPosicion = 0;
        for (int i = 0; i < destinos.length; i++) {                 //Ciclo for que recorre las posiciones de los detinos
            if (destinos[i].compareToIgnoreCase(antofa) == 0) {     //condicion para comparar el string antofagasta con los destinos
                auxPosicion = i;                                    //auxiliar que almacena la posicion
                for (int j = 0; j < pasajeros.length; j++) {        //ciclo que recorre las posiciones de la matriz pasajeros
                    if (pasajeros[j][i] > max) {                    //condicion que verifica si el numero en la matriz es mayor que el auxiliar max
                        max = pasajeros[j][i];                      //auxiliar para almacenar el numero mayor
                    }//fin condicion de verificacion
                }//fin ciclo pasajeros
            }//fin condicion de comparacion
        }//fin ciclo destinos
        
        for (int i = 0; i < pasajeros.length; i++) {    //Ciclo que recorre las posiciones en pasajeros
            if (pasajeros[i][auxPosicion] == max) {     //condicion que compara el maximo con las posiciones
                StdOut.print("Desde: ");
                StdOut.println(origenes[i] + ", Han salido la mayor cantidad de pasajeros hacia Antofagasta, con: " + max + " Pasajeros.");
            }//fin condicion
        }//fin ciclo
    }//fin metodo

    /**
     * Subprograma metodo para eliminar las condiciones de las ciudades que han llegado menos de 30 pasajeros
     * y han salido menos de 80 pasajeros.
     * @param origenes vector origenes para leer todos los origenes ya almacenados
     * @param destinos vector destinos para leer todos los destinos ya almacenados
     * @param pasajeros matriz de pasajeros que vincula los origenes y los destinos con la cantidad de pasajeros
     */
    private static void EliminarC(String[] origenes, String[] destinos, int[][] pasajeros) {
        int sumaParcial1 = 0; //origenes
        int sumaParcial2 = 0; //destinos
        
        for (int i = 0; i < origenes.length; i++) {     //Ciclo que recorre todas las posiciones de los origenes
            sumaParcial1 = 0;                           //auxiliar para la suma
            for (int j = 0; j < destinos.length; j++) { //ciclo que recorre todas las posiciones de los destinos
                sumaParcial1 += pasajeros[i][j];        //auxiliar que suma los pasajeros por cada destino
            }
            sumaParcial2 = 0;                           //auxiliar simas
            for (int j = 0; j < origenes.length; j++) { //ciclo que recorre todas las posiciones de los origenes
                sumaParcial2 += pasajeros[j][i];        //auxiliar que suma los pasajeros por cada origen
            }
            
            if ((sumaParcial2 < 30 && sumaParcial1 < 80) && (origenes[i] != "0" || destinos[i] != "0")) { //condicion que verifica que la suma de los origenes sea mayor a 30 y destinos mayor a 80
                if (sumaParcial2 < 30) {    //condicion para separar la condicion anterior
                    for (int j = i; j < (destinos.length - 1); j++) {   //ciclo que recorre las posiciones de los destinos
                        destinos[j] = destinos[j+1];                    //algoritmo de corrimiento para los destinos
                        for (int k = 0; k < pasajeros.length; k++) {    //ciclo que recorre todas las posiciones de los pasajeros
                            pasajeros[k][j] = pasajeros[k][j+1];        //algoritmo de corrimiento para la matriz pasajeros
                        }
                    }
                }
                if (sumaParcial1 < 80) {    //condicion para separar la condicion principal
                    for (int j = i; j < (origenes.length -1); j++) {    //ciclo que recorre las posiciones de los origenes
                        origenes[j] = origenes[j+1];                    //algoritmo de corrimiento para los origenes
                        for (int k = 0; k < pasajeros.length; k++) {    //ciclo que recorre las posiciones de los pasajeros
                            pasajeros[j][k] = pasajeros[j+1][k];        //algoritmo de corrimiento para la matriz pasajeros
                        }//fin ciclo        
                    }//fin ciclo
                }//fin condicion
            }//fin condicion principal
        }//fin ciclo
    }//fin metodo

    /**
     * Subprograma metodo que crea un nuevo archivo similar al metodo GenerarArchivoA
     * con los arreglos de EliminarC ya realizados.
    * @param origenes Lee las ciudades registradas en el vector origen
    * @param destinos Lee las ciudades registradas en el vector destino
    * @param pasajeros Lee las cantidades de pasajeros 
    * @param ciudades Vector auxiliar que almacena la totalidad de las ciudades
     */
    private static void GenerarArchivoB(String[] origenes, String[] destinos, int[][] pasajeros, String[] ciudades) {
        Out salida = new Out("textos/CiudadesVersion2.txt");
        int aux1 = 0;
        int aux2 = 0;
        boolean aux3 = true;
        boolean aux4 = true;
           
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] != "0") {
                for (int j = 0; j < origenes.length; j++) {
                    if (ciudades[i].compareTo(origenes[j]) == 0) {
                        salida.print("En la ciudad de: " + ciudades[i]);
                        int suma2 = 0;
                        for (int k = 0; k < origenes.length; k++) {
                            suma2 += pasajeros[j][k];
                        }
                        aux4 = true;
                        for (int k = 0; k < destinos.length; k++) {
                            if (origenes[j].contains(destinos[k])) {
                                int suma3 = 0;
                                for (int l = 0; l < 10; l++) {
                                    suma3 += pasajeros[l][k];
                                }
                                salida.print(", Entraron: " + suma3 + " Pasajeros.");
                                aux4 = false;
                            }                            
                        }
                        if (aux4 == true) {
                            salida.print(" Entraron: 0 Pasajeros.");
                        }
                        salida.println(" Y Salieron: " + suma2 + " Pasajeros.");
                        break;
                    }
                    
                    if (ciudades[i].compareTo(origenes[j]) != 0 && ciudades[i].compareTo(destinos[j]) == 0) {
                        salida.print("En la ciudad de: " + ciudades[i]);
                        int suma = 0;
                        for (int k = 0; k < destinos.length; k++) {
                            suma += pasajeros[k][j];
                        }
                        
                        salida.print(", Entraron: " + suma + " Pasajeros.");
                        aux3 = true;
                        for (int k = 0; k < origenes.length; k++) {
                            if (destinos[j].contains(origenes[k])) {
                                suma = 0;
                                for (int l = 0; l < origenes.length; l++) {
                                    suma += pasajeros[k][l];
                                }
                                salida.println(" Y Salieron: " + suma + " Pasajeros.");
                                aux3 = false;
                            }
                        }
                        if (aux3 == true) {
                            salida.println(" Y Salieron: 0 Pasajeros.");
                        }
                        break;
                    }   
                    aux2++;
                }
                aux2 = 0;
            }   
        }
    }
}
