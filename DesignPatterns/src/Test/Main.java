package Test;

public class Main {
    public static void main(String[] args) {
        Computer.Builder builder = Computer.builder()
                .withGraphicCard("RTX")
                .withProcessor("Ryzen 7")
                .withKeyboard("keyboard")
                .withMonitor("Dell")
                .withRam("64 GB");

        //throw ex, because some of the fields are mandatory
        Computer computer = Computer.builder()
                .withGraphicCard("RTX")
                .withProcessor("Ryzen 7")
                .withKeyboard("keyboard")
                .withMonitor("Dell")
                .withRam("64 GB")
                .withSsd("Kingston")
                .withMotherboard("motherboard") //without this for example
                .build();

    }

}
