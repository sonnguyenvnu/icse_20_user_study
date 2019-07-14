/** 
 * Returns the jar file from which the given class is loaded; or null if no such jar file can be located.
 */
public static JarFile jarFileOf(final Class<?> klass){
  URL url=klass.getResource("/" + klass.getName().replace('.','/') + ".class");
  if (url == null) {
    return null;
  }
  String s=url.getFile();
  int beginIndex=s.indexOf("file:") + "file:".length();
  int endIndex=s.indexOf(".jar!");
  if (endIndex == -1) {
    return null;
  }
  endIndex+=".jar".length();
  String f=s.substring(beginIndex,endIndex);
  f=URLDecoder.decode(f,"UTF-8");
  File file=new File(f);
  try {
    return file.exists() ? new JarFile(file) : null;
  }
 catch (  IOException e) {
    throw new IllegalStateException(e);
  }
}
