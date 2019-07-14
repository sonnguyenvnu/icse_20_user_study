private void normalize(Properties props){
  ArrayList<String> toBeNormalized=new ArrayList<String>(10);
  for (  Object key : props.keySet()) {
    String keyStr=(String)key;
    if (-1 != (keyStr.indexOf("twitter4j."))) {
      toBeNormalized.add(keyStr);
    }
  }
  for (  String keyStr : toBeNormalized) {
    String property=props.getProperty(keyStr);
    int index=keyStr.indexOf("twitter4j.");
    String newKey=keyStr.substring(0,index) + keyStr.substring(index + 10);
    props.setProperty(newKey,property);
  }
}
