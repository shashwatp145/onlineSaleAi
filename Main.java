import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Outcome> singleCoinOutcome = new ArrayList<>();
        
        singleCoinOutcome.add(new Outcome("Head", 35)); //storing coin outcome of heads
        singleCoinOutcome.add(new Outcome("Tail", 65)); //storing coin outcome of tails
        
        eventSimulator biasedCoinSimulator = new eventSimulator(singleCoinOutcome); 
        String coinResult = biasedCoinSimulator.simulateEvent(1000);
        
        List<Outcome> diceOutcomes = new ArrayList<>(); //for dice outcomes
        diceOutcomes.add(new Outcome("1", 10));
        diceOutcomes.add(new Outcome("2", 30));
        diceOutcomes.add(new Outcome("3", 15));
        diceOutcomes.add(new Outcome("4", 15));
        diceOutcomes.add(new Outcome("5", 30));
        diceOutcomes.add(new Outcome("6", 0));
        
        eventSimulator biasedDiceSimulator = new eventSimulator(diceOutcomes);
        String diceResult = biasedDiceSimulator.simulateEvent(1000);
        
        System.out.println("Coin Simulation result after 1000 occurrences: " + coinResult);
        System.out.println("Dice Simulation result after 1000 occurrences: " + diceResult);
    }
}


class eventSimulator {
    private List<Outcome> outcomes;
    private Random random;

    public eventSimulator(List<Outcome> outcomes) {
        this.outcomes = outcomes;
        this.random = new Random();
    }

    public String simulateEvent(int countOfOccurences) {
        int[] outcomeCounts = new int[outcomes.size()];
        
        for (int i = 0; i < countOfOccurences; i++) {
            double rand = random.nextDouble() * 100; // Get a random number between 0 and 100
            double cumulativeProbability = 0;
            
            for (int j = 0; j < outcomes.size(); j++) {
                Outcome outcome = outcomes.get(j);
                cumulativeProbability += outcome.getProbability();
                
                if (rand <= cumulativeProbability) {
                    outcomeCounts[j]++;
                    break;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < outcomes.size(); i++) {
            Outcome outcome = outcomes.get(i);
            int count = outcomeCounts[i];
            result.append(outcome.getName()).append(": ").append(count).append(" times, ");
        }
        
        return result.toString();
    }
}

class Outcome {
    private String name;
    private double probability;

    public Outcome(String name, double probability) {
        this.name = name;
        this.probability = probability;
    }

    public String getName() {
        return name;
    }

    public double getProbability() {
        return probability;
    }
}

