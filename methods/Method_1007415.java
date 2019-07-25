/** 
 * Dump.
 * @param attr the attr
 * @return the string
 */
public static String dump(Attributes attr){
  String version=getAttribute(attr,null,VERSIONS);
  String pack=getAttribute(attr,null,PACKAGE);
  String title=getAttribute(attr,null,TITLE);
  return "version=" + version + ", package=" + pack + ", title=" + title;
}
