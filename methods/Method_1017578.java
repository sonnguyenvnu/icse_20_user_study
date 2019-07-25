@CliCommand(value="cd lcd",help="Prints CD player lcd info") public String lcd(){
  return cdPlayer.getLdcStatus();
}
