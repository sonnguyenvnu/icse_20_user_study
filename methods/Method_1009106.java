@Override public void element(String name,String text){
  try {
    this.xml.element(name,text);
  }
 catch (  IOException ex) {
    doNothing();
  }
}
