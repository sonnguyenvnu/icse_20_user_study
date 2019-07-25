@CliCommand(value="sm variables",help="Prints extended state variables") public String variables(){
  StringBuilder buf=new StringBuilder();
  Set<Entry<Object,Object>> entrySet=stateMachine.getExtendedState().getVariables().entrySet();
  Iterator<Entry<Object,Object>> iterator=entrySet.iterator();
  if (entrySet.size() > 0) {
    while (iterator.hasNext()) {
      Entry<Object,Object> e=iterator.next();
      buf.append(e.getKey() + "=" + e.getValue());
      if (iterator.hasNext()) {
        buf.append("\n");
      }
    }
  }
 else {
    buf.append("No variables");
  }
  return buf.toString();
}
