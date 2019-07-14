private String parseClassName(String name){
  if (name == null) {
    return null;
  }
  String className=name;
  int n=name.lastIndexOf('/');
  if (n > -1) {
    className=name.substring(n + 1);
  }
  name=name.replace('/','.');
  packages.put(className,name);
  n=className.indexOf('$');
  if (n > -1) {
    packages.put(className.substring(n + 1),name);
    packages.put(className.replace('$','.'),name);
  }
  return name;
}
