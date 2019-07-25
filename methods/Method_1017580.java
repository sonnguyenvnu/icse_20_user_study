@CliCommand(value="cd load",help="Load CD into player") public String load(@CliOption(key={"","index"}) int index){
  StringBuilder buf=new StringBuilder();
  try {
    Cd cd=library.getCollection().get(index);
    cdPlayer.load(cd);
    buf.append("Loading cd " + cd);
  }
 catch (  Exception e) {
    buf.append("Cd with index " + index + " not found, check library");
  }
  return buf.toString();
}
