public void open(){
  try {
    setClosed(false);
    setVisible(true);
  }
 catch (  final PropertyVetoException e) {
    Log.error(this,e);
  }
}
