default void clear(){
  for (  String property : keySet()) {
    setProperty(property,null);
  }
}
