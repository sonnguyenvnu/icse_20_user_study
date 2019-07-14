/** 
 * Converts java.lang.String into String, etc
 */
static private String removePackagePrefixes(String input){
  if (!input.contains(".")) {
    return input;
  }
  String[] names=PApplet.split(input,',');
  StringList result=new StringList();
  for (  String name : names) {
    int dot=name.lastIndexOf('.');
    if (dot >= 0) {
      name=name.substring(dot + 1,name.length());
    }
    result.append(name);
  }
  return result.join(", ");
}
