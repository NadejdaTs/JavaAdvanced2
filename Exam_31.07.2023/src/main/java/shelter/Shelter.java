package shelter;

import java.util.ArrayList;
import java.util.List;

public class Shelter {
    private int capacity;
    private List<Animal> data;

    public Shelter(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Animal animal){
        if(this.data.size() < this.capacity){
            this.data.add(animal);
        }
    }

    public boolean remove(String name){
        for (Animal animal : this.data) {
            if(animal.getName().equals(name)){
                this.data.remove(animal);
                return true;
            }
        }
        return false;
    }

    public Animal getAnimal(String name, String caretaker){
        for (Animal animal : this.data) {
            if(animal.getName().equals(name) && animal.getCaretaker().equals(caretaker)){
                return animal;
            }
        }
        return null;
    }

    public Animal getOldestAnimal(){
        Animal oldestAnimal = null;
        int maxAge = 0;
        for (Animal animal : this.data) {
            if(animal.getAge() >= maxAge){
                oldestAnimal = animal;
                maxAge = animal.getAge();
            }
        }
        return oldestAnimal;
    }

    public int getCount(){
        return this.data.size();
    }

    public String getStatistics(){
        StringBuilder sb = new StringBuilder();
        for (Animal animal : this.data) {
            sb.append(animal.getName()).append(" ");
            sb.append(animal.getCaretaker()).append(System.lineSeparator());
        }
        return String.format("The shelter has the following animals:%n" + sb);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Animal> getData() {
        return data;
    }

    public void setData(List<Animal> data) {
        this.data = data;
    }
}
