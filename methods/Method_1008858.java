private static void init(){
  properties=new Properties();
  try {
    properties.load(ResourceUtils.getResource("docx4j.properties"));
  }
 catch (  Exception e) {
    log.warn("Couldn't find/read docx4j.properties; " + e.getMessage());
  }
}
