public void startArray(){
  if (context != null) {
    beginStructure();
  }
  context=new JSONStreamContext(context,StartArray);
  writer.write('[');
}
