public static String getValue(String key){
  try {
    String value=config.getProperty(key);
    return value;
  }
 catch (  Exception e) {
    e.printStackTrace();
    System.err.println("ConfigInfoError" + e.toString());
    return null;
  }
}
