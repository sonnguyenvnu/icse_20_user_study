/** 
 * Work data initialization.
 */
public void init(String name,final String superName,final String suffix,final String reqProxyClassName){
  int lastSlash=name.lastIndexOf('/');
  this.targetPackage=lastSlash == -1 ? StringPool.EMPTY : name.substring(0,lastSlash).replace('/','.');
  this.targetClassname=name.substring(lastSlash + 1);
  this.nextSupername=superName;
  this.superName=name;
  if (reqProxyClassName != null) {
    if (reqProxyClassName.startsWith(DOT)) {
      name=name.substring(0,lastSlash) + '/' + reqProxyClassName.substring(1);
    }
 else     if (reqProxyClassName.endsWith(DOT)) {
      name=reqProxyClassName.replace('.','/') + this.targetClassname;
    }
 else {
      name=reqProxyClassName.replace('.','/');
    }
  }
  if (suffix != null) {
    name+=suffix;
  }
  this.thisReference=name;
  this.superReference=this.superName;
}
