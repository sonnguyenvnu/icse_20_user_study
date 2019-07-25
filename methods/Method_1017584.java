@CliCommand(value="persist deliver",help="Deliver order") public void deliver(@CliOption(key={"","id"},help="Order id") int order){
  persist.change(order,"DELIVER");
}
