package DataLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import Utils.ToStringHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class FileWriterClass {
    static ToStringHelper toStringHelper=new ToStringHelper(" ","->");

    public static void printBill(Order order, String clientName, List<MenuItem> orderedProducts, double totalPrice) {

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("BillNo"+ order.getOrderID()+".txt", false);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.println("ORDER DETAILS:");
            pw.println();
            pw.println("Client Name:"+clientName);
            pw.println(order);
            pw.println("Products:");
            for(MenuItem m: orderedProducts){
                pw.print(m);
            }
            pw.println();
            pw.println("Total Price:" + totalPrice);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {// can't do anything }
            }

        }
    }

    public static <T> void printReport(Collection<T> c){

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("Report.txt", false);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);


            pw.println(toStringHelper.toString((List<?>) c));


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {// can't do anything }
            }

        }
    }

    public static void printReport(Map<?,?> m){

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("Report.txt", false);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.println(toStringHelper.toString(m));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {// can't do anything }
            }

        }
    }
}
