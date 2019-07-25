/** 
 * Obtains the URL.
 */
@Override public URL find(String classname){
  if (this.classname.equals(classname)) {
    String cname=classname.replace('.','/') + ".class";
    try {
      return new URL(null,"file:/ByteArrayClassPath/" + cname,new BytecodeURLStreamHandler());
    }
 catch (    MalformedURLException e) {
    }
  }
  return null;
}
