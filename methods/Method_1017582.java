@CliCommand(value="persist process",help="Process order") public void process(@CliOption(key={"","id"},help="Order id") int order){
  persist.change(order,"PROCESS");
}
