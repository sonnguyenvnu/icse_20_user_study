public static JanusGraphManager getInstance(){
  try {
    return JanusGraphManager.getInstance();
  }
 catch (  NoClassDefFoundError e) {
    return null;
  }
}
