/** 
 * ??thymeleaf??
 * @param path
 * @return
 */
public static String thymeleaf(String path){
  String folder=PropertiesFileUtil.getInstance().get("app.name");
  return "/".concat(folder).concat(path).concat(".html");
}
