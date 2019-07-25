private final void bpa(String s){
  addHeir(null);
  if (System.getProperty("TLA-StackTrace","off").equals("on"))   ToolIO.out.println("Beginning " + s);
  pushMsg(s,getToken(1));
  expecting=emptyString;
}
