public boolean name(String name){
  if (name.equalsIgnoreCase("GET")) {
    return isGet();
  }
 else {
    return this.nettyMethod.name().equalsIgnoreCase(name);
  }
}
