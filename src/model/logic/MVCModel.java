package model.logic;


import model.data_structures.DoublyLinkedList;
import model.data_structures.Queue;
import model.data_structures.RedBlackTree;
import model.value_objects.TravelArea;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Definicion del modelo del mundo
 */
public class MVCModel {

    private RedBlackTree<Integer, TravelArea> areasData;



    public MVCModel() {
        areasData = new RedBlackTree<>();

    }

    public RedBlackTree<Integer, TravelArea> areasData() {
        return areasData;
    }


    public void loadData() {

        DataLoader d = new DataLoader();

        d.loadTravelAreas(areasData);
    }
    
    public TravelArea getTravelArea(int ID) {

        return areasData.get(ID);
    }


    public DoublyLinkedList<TravelArea> getTravelAreas(int low, int hi){

        DoublyLinkedList<TravelArea> list = new DoublyLinkedList<>();

        for (Iterator<Integer> it = areasData.keysInRange(low, hi); it.hasNext(); ) {
            Integer temp = it.next();

            list.addLast(areasData.get(temp));
        }

        list.mergeSort(new IDComparator());

        return  list;

    }

    private class IDComparator implements Comparator<TravelArea> {

        @Override
        public int compare(TravelArea o1, TravelArea o2) {
            if (o1.getID() < o2.getID()) return -1;
            if (o1.getID() > o2.getID()) return 1;
            else return 0;
        }
    }

    



}