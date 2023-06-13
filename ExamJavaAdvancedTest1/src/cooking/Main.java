package cooking;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> liquidsQueue = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> ingredientsStack = new ArrayDeque<>();

        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(ingredientsStack::push);

        Map<Integer, String> mapOfPrices = new LinkedHashMap<>();
        mapOfPrices.put(25, "Bread");
        mapOfPrices.put(50, "Cake");
        mapOfPrices.put(100, "Fruit Pie");
        mapOfPrices.put(75, "Pastry");

        Map<String, Integer> resultMap = new TreeMap<>();
        resultMap.put("Bread", 0);
        resultMap.put("Cake", 0);
        resultMap.put("Fruit Pie", 0);
        resultMap.put("Pastry", 0);

        while(!liquidsQueue.isEmpty() && !ingredientsStack.isEmpty()){
            int liquid = liquidsQueue.poll();
            int ingredient = ingredientsStack.peek();

            int sum = liquid + ingredient;
            for (Integer s : mapOfPrices.keySet()) {
                if(sum == s){
                    ingredientsStack.pop();
                    String name = mapOfPrices.get(s);
                    int value = resultMap.get(mapOfPrices.get(s)) + 1;
                    resultMap.put(name, value);
                    break;
                }
                if(sum != s && s == 75){
                    ingredientsStack.pop();
                    ingredientsStack.push(ingredient + 3);
                }
            }
        }
        boolean isEverythingCooked = true;
        for (Integer s : resultMap.values()) {
            if(s == 0){
                isEverythingCooked = false;
            }
        }
        if(isEverythingCooked){
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        }else{
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        }

        System.out.print("Liquids left: ");
        if(liquidsQueue.isEmpty()){
            System.out.println("none");
        }else{
            List<Integer> list = new ArrayList<>();
            liquidsQueue.forEach(list::add);
            String result = list.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            System.out.println(result);
        }

        System.out.print("Ingredients left: ");
        if(ingredientsStack.isEmpty()){
            System.out.println("none");
        }else{
            List<Integer> list = new ArrayList<>();
            ingredientsStack.forEach(list::add);
            String result = list.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println(result);


            /*liquidsQueue.stream()
                    .map(String::valueOf)
                    //.collect(Collectors.joining(", "));
                    .forEach(System.out.print(String.join((CharSequence) ", ", list)));
                    //.forEach(e -> System.out.print(e + ", "));*/

        }

        for (String s : resultMap.keySet()) {
            System.out.printf("%s: %d%n", s, resultMap.get(s));
        }
    }
}
