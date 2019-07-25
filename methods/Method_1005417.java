private void dispense(){
  if (state == SOLD) {
    System.out.println("A gumball comes rolling out the slot");
    count=count - 1;
    if (count == 0) {
      System.out.println("Oops, out of gumballs!");
      state=SOLD_OUT;
    }
 else {
      state=NO_QUARTER;
    }
  }
 else   if (state == NO_QUARTER) {
    System.out.println("You need to pay first");
  }
 else   if (state == SOLD_OUT) {
    System.out.println("No gumball dispensed");
  }
 else   if (state == HAS_QUARTER) {
    System.out.println("No gumball dispensed");
  }
}
