package controller;

import java.util.Iterator;
import java.util.Scanner;

import model.data_structures.DoublyLinkedList;
import model.logic.MVCModel;
import model.value_objects.TravelArea;
import view.MVCView;

public class Controller {



    private MVCModel model;


    private MVCView view;


    public Controller() {
        model = new MVCModel();
        view = new MVCView();
    }


    public void run() {

        Scanner reader = new Scanner(System.in);
        boolean stop = false;


        int ID = -1;

        int lo = -1;
        int hi = -1;


        while (!stop) {
            view.printMenu();

            int option = reader.nextInt();

            switch (option) {
                case 1:

                    model.loadData();

                    System.out.println("Para el primer semestre del 2018 se leyeron las siguientes cantidades de zonas de viaje: " + model.areasData().size() + "\n");
                    System.out.println("El valor mayor de ID es: " + model.areasData().getMaxValue().getID() + "\n");
                    System.out.println("El valor menor de ID es: " + model.areasData().getMinValue().getID() + "\n");

                    break;

                case 2:

                        System.out.println(" \n Ingresar ID: \n");

                        try {
                            ID = reader.nextInt();
                        } catch (Exception e) {
                            System.out.println("Debe ingresar un n�mero");
                        }

                        TravelArea area = model.areasData().get(ID);

                        if (area == null){
                            System.out.println("No existe una zona con dicho ID");
                        }
                        else {
                            System.out.println(area.toString());
                        }

                        break;


                case 3:

                    System.out.println(" \n Ingresar valor menor: \n");

                    try {
                        lo = reader.nextInt();
                    } catch (Exception e) {
                        System.out.println("Debe ingresar un n�mero");
                    }

                    System.out.println(" \n Ingresar valor mayor: \n");

                    try {
                        hi = reader.nextInt();
                    } catch (Exception e) {
                        System.out.println("Debe ingresar un n�mero");
                    }


                    DoublyLinkedList<TravelArea> areas = model.getTravelAreas(lo, hi);

                    printList(areas);

                    break;



                default: {
                    System.out.println("Opcion invalida");
                }
            }
        }
    }

    private void printList(DoublyLinkedList<TravelArea> lista) {
        TravelArea temp;
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %30s %20s  %20s  %20s", "ID", "Name", "Perimeter", "Area", "Number of points");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------");

        Iterator<TravelArea> iter = null;
        if (lista != null) {

            iter = lista.iterator();

            while (iter.hasNext()) {
                temp = iter.next();
                System.out.format("%10s %30s %20s  %20s  %20s", temp.getID(), temp.getName(), temp.getPerimeter(), temp.getArea(), temp.getFrontier().length);
                System.out.println();
            }
        }
    }

}
