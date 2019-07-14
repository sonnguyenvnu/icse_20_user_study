public static JanusGraphManager getInstance(boolean forceCreate){
  if (forceCreate) {
    return new JanusGraphManager(new Settings());
  }
 else {
    return instance;
  }
}
