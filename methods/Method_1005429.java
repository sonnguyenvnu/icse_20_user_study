public void dispense(){
  gumballMachine.releaseBall();
  if (gumballMachine.getCount() == 0) {
    gumballMachine.setState(gumballMachine.getSoldOutState());
  }
 else {
    gumballMachine.releaseBall();
    System.out.println("YOU'RE A WINNER! You got two gumballs for your quarter");
    if (gumballMachine.getCount() > 0) {
      gumballMachine.setState(gumballMachine.getNoQuarterState());
    }
 else {
      System.out.println("Oops, out of gumballs!");
      gumballMachine.setState(gumballMachine.getSoldOutState());
    }
  }
}
