@CliCommand(value="cd library",help="List user CD library") public String library(){
  SimpleDateFormat format=new SimpleDateFormat("mm:ss");
  StringBuilder buf=new StringBuilder();
  int i1=0;
  for (  Cd cd : library.getCollection()) {
    buf.append(i1++ + ": " + cd.getName() + "\n");
    int i2=0;
    for (    Track track : cd.getTracks()) {
      buf.append("  " + i2++ + ": " + track.getName() + "  " + format.format(new Date(track.getLength() * 1000)) + "\n");
    }
  }
  return buf.toString();
}
