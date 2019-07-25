public void close(){
  try {
    setClosed(true);
  }
 catch (  final PropertyVetoException e) {
    Log.error(this,e);
  }
}
