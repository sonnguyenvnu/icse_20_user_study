/** 
 * Load icon from name.
 * @param name File name.
 * @return Image by name.
 */
private static Image load(String name){
  try {
    String file="resources/icons/" + name + ".png";
    URL url=Thread.currentThread().getContextClassLoader().getResource(file);
    return new Image(url.openStream());
  }
 catch (  Exception e) {
    Logging.fatal(e);
    return null;
  }
}
