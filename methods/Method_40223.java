@Nullable public static String moduleQname(@NotNull String file){
  File f=new File(file);
  if (f.getName().endsWith("__init__.py")) {
    file=f.getParent();
  }
 else   if (file.endsWith(Analyzer.self.suffix)) {
    file=file.substring(0,file.length() - Analyzer.self.suffix.length());
  }
  return file.replace(".","%20").replace('/','.').replace('\\','.');
}
