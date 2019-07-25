@Override public URL find(String classname){
  String jarname=classname.replace('.','/') + ".class";
  if (jarfileEntries.contains(jarname))   try {
    return new URL(String.format("jar:%s!/%s",jarfileURL,jarname));
  }
 catch (  MalformedURLException e) {
  }
  return null;
}
