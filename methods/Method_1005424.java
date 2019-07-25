void refill(int count){
  this.count+=count;
  System.out.println("The gumball machine was just refilled; it's new count is: " + this.count);
  state.refill();
}
