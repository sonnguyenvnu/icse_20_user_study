@Override public URL find(String classname){
  char sep=File.separatorChar;
  String filename=directory + sep + classname.replace('.',sep) + ".class";
  File f=new File(filename);
  if (f.exists())   try {
    return f.getCanonicalFile().toURI().toURL();
  }
 catch (  MalformedURLException e) {
  }
catch (  IOException e) {
  }
  return null;
}
