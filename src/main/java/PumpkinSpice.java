
public class PumpkinSpice extends CoffeeDecorator{

    private double cost = .70;
    PumpkinSpice(Coffee specialCoffee){
        super(specialCoffee);
    }

    public double makeCoffee() {
        return specialCoffee.makeCoffee() + addPumpkinSpice();
    }

    private double addPumpkinSpice() {

        System.out.println(" + pumpkin spice: $.70");

        return cost;
    }

    public double getInitialCost() {        // the original cost of one pumpkinSpice addition
        return 0.70;
    }
}
