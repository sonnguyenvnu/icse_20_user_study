@Override public void install(String id){
  try {
    Thread.sleep((long)(Math.random() * 10000));
    Extension extension=getExtension(id,null);
    extension.setInstalled(true);
    postInstalledEvent(id);
  }
 catch (  InterruptedException e) {
  }
}
