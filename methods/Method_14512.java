public void save(Writer writer){
  try {
    ParsingUtilities.defaultWriter.writeValue(writer,this);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
