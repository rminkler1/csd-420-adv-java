public class Enumeration {

    // A more complex enum with a description value
    enum Day {
        SUNDAY("Sunday fun day."),
        MONDAY("The dreaded Monday."),
        TUESDAY("Just another day."),
        WEDNESDAY("Hump day."),
        THURSDAY("Almost there."),
        FRIDAY("Last day of the week."),
        SATURDAY("The weekend has arrived.");

        private final String description;

        Day(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }


    // A simple enum
    public enum Speed {
        STOPPED,
        SLOW,
        MEDIUM,
        FAST
    }

    private static class Fan {
        private Speed speed;

        Fan() {
            speed = Speed.STOPPED;
        }

        Fan(Speed speed) {
            this.speed = speed;
        }

        public Speed getSpeed() {
            return speed;
        }

        public void setSpeed(Speed speed) {
            this.speed = speed;
        }

        public void speedUp() {
            switch (speed) {
                case STOPPED -> speed = Speed.SLOW;
                case SLOW -> speed = Speed.MEDIUM;
                case MEDIUM, FAST -> speed = Speed.FAST;
                default -> speed = Speed.STOPPED;
            }
        }
    }

    public static void main(String[] args) {
        Fan fan1 = new Fan();
        fan1.speedUp();
        fan1.speedUp();
        fan1.speedUp();
        fan1.speedUp();

        System.out.println(fan1.getSpeed());

        // Using Day enum and accessing the description and ordinal
        Day today = Day.MONDAY;
        System.out.println(today.getDescription());
        System.out.println(Day.SUNDAY.ordinal());
        System.out.println(Day.MONDAY.ordinal());
        System.out.println(Day.TUESDAY.ordinal());
    }
}
