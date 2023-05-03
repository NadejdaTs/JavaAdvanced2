package Test;

public class Computer {

    public static class Builder{
        private Computer instance;

        private Builder(){
            instance = new Computer();
        }

        public Builder withProcessor(String processor){
            instance.processor = processor;
            return this;
        }
        public Builder withRam(String ram){
            instance.ram = ram;
            return this;
        }
        public Builder withMotherboard(String motherboard){
            instance.motherboard = motherboard;
            return this;
        }
        public Builder withGraphicCard(String graphicCard){
            instance.graphicCard = graphicCard;
            return this;
        }
        public Builder withSsd(String ssd){
            instance.ssd = ssd;
            return this;
        }

        public Builder withMonitor(String monitor){
            instance.monitor = monitor;
            return this;
        }

        public Builder withKeyboard(String keyboard){
            instance.keyboard = keyboard;
            return this;
        }

        public Builder withMouse(String mouse){
            instance.mouse = mouse;
            return this;
        }

        public Computer build() {
            //mandatory fields
            if(instance.processor == null || instance.ram == null || instance.motherboard == null || instance.graphicCard == null || instance.ssd == null){
                throw new IllegalArgumentException("Some element of computer are mandatory!");
            }
            return this.instance;
        }
    }

    private String processor;
    private String ram;
    private String motherboard;
    private String graphicCard;
    private String ssd;
    private String monitor;
    private String keyboard;
    private String mouse;

    private Computer(){
    }

    public static Builder builder(){
        return new Builder();
    }
}
