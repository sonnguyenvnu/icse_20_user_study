@CliCommand(value="persist send",help="Send order") public void send(@CliOption(key={"","id"},help="Order id") int order){
  persist.change(order,"SEND");
}
