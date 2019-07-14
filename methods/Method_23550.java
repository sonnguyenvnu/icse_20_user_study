static protected StringDict parseStyleAttributes(String style){
  StringDict table=new StringDict();
  if (style != null) {
    String[] pieces=style.split(";");
    for (int i=0; i < pieces.length; i++) {
      String[] parts=pieces[i].split(":");
      table.set(parts[0],parts[1]);
    }
  }
  return table;
}
