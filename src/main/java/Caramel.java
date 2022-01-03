
public class Caramel extends CoffeeDecorator{

    private double cost = .60;
    Caramel(Coffee specialCoffee){
        super(specialCoffee);
    }

    public double makeCoffee() {
        return specialCoffee.makeCoffee() + addCaramel();
    }

    private double addCaramel() {

        System.out.println(" + caramel: $.60");

        return cost;
    }

    public double getInitialCost() {    // the original cost of one Caramel addition
        return 0.60;
    }

}
