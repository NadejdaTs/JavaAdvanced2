package Bouquets;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> tulipStack = new ArrayDeque<>();

        Arrays.stream(sc.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(tulipStack::push);

        ArrayDeque<Integer> daffodilQueue = Arrays.stream(sc.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int sumOfFlowers = 0;
        int readyBouquets = 0;
        while(!tulipStack.isEmpty() && !daffodilQueue.isEmpty()){
            int currTulip = tulipStack .pop();
            int currDaffodil = daffodilQueue.poll();
            int sum = currTulip + currDaffodil;

            if(sum == 15){
                readyBouquets++;
            }else if(sum > 15){
                int newSum = sum;
                while(newSum > 15){
                    currTulip -= 2;
                    newSum = currTulip + currDaffodil;
                    if(newSum == 15){
                        readyBouquets++;
                    }
                }
                if(newSum != 15){
                    sumOfFlowers += newSum;
                }
            }else if(sum < 15){
                sumOfFlowers += sum;
            }
        }
        readyBouquets += sumOfFlowers / 15;
        if(readyBouquets >= 5){
            System.out.printf("You made it! You go to the competition with %d bouquets!", readyBouquets);
        }else{
            System.out.printf("You failed... You need more %d bouquets.", (5 - readyBouquets));
        }
    }
}
