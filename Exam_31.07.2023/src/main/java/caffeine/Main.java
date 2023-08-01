package caffeine;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> caffeineMg = new ArrayDeque<>();

        Arrays.stream(sc.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(caffeineMg::push);

        ArrayDeque<Integer> energyDrink = Arrays.stream(sc.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int caffeinePerDay = 0;
        while(!caffeineMg.isEmpty() && !energyDrink.isEmpty()){

            int currMg = caffeineMg.pop();
            int currDrink = energyDrink.peek();
            int currSum = currMg * currDrink;

            if(caffeinePerDay + currSum <= 300){
                caffeinePerDay += currSum;
                energyDrink.poll();
            }else{
                energyDrink.poll();
                energyDrink.offer(currDrink);
                if(caffeinePerDay > 0){
                    caffeinePerDay -= 30;
                }
            }
        }

        if(!energyDrink.isEmpty()) {
            String leftDrinks = energyDrink.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            System.out.printf("Drinks left: %s%n", leftDrinks);
        }else {
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        }
        System.out.printf("Stamat is going to sleep with %d mg caffeine.%n", caffeinePerDay);
    }
}