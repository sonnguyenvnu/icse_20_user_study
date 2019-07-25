private static boolean contains(String key,String value){
  try {
    String tmp=System.getProperty(key);
    return tmp != null && tmp.trim().toLowerCase().contains(value.trim().toLowerCase());
  }
 catch (  Throwable t) {
    return false;
  }
}
