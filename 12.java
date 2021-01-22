import java.io.*;
import java.util.*;

class pro12 {
    private static int packetNo, bucketCapacity, arraySize, store, packetLoss, dataFlow;
    private static int array[];

    public void LeakyBucket() {
        store = 0;
        System.out.print("\nCurrent Bucket size :" + store);
        for (int i = 0; i < arraySize; i++) {
            int input = array[i];
            System.out.print("\n----------------------\nInput to bucket is " + input);
            packetLoss = 0;
            store = store + input;
            System.out.print("\nCurrent Bucket size :" + store);
            if (store <= dataFlow)
                store = 0;
            else {
                store = store - dataFlow;
            }

            if (store <= bucketCapacity) {
                System.out.print("\nNo Packet Loss");
            } else {
                packetLoss = (store - bucketCapacity);
                store = bucketCapacity;
                System.out.print("\nPacket Loss :" + packetLoss);
            }
            System.out.print("\nAfter Sending packets, Bucket size is :" + store);

        }
    }

    public static void main(String args[]) {
        pro12 pr = new pro12();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Bucket Capacity : ");
        bucketCapacity = scan.nextInt();
        System.out.print("\nEnter the Bucket Fixed Output Data Flow : ");
        dataFlow = scan.nextInt();
        System.out.print("\nEnter the number of Itterations : ");
        arraySize = scan.nextInt();
        System.out.println("\nEnter the Input values of size : " + arraySize);
        array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = scan.nextInt();
        }

        System.out.println("The Input for LeakyBucket is ");
        for (int i = 0; i < arraySize; i++) {
            System.out.print(array[i] + " ");
        }

        pr.LeakyBucket();
        System.out.print("\n\nProgram Succesful...\n");
        scan.close();
    }

}