private void fail(File file,String error,int line){
  if (line <= 0) {
    line=1;
  }
  String name=file.getAbsolutePath();
  int idx=name.lastIndexOf(File.separatorChar);
  if (idx >= 0) {
    name=name.replace(File.separatorChar,'.');
    name=name + "(" + name.substring(idx + 1) + ":" + line + ")";
    idx=name.indexOf("org.");
    if (idx > 0) {
      name=name.substring(idx);
    }
  }
  System.out.println("FAIL at " + name + " " + error + " " + file.getAbsolutePath());
  hasError=true;
  if (failOnError) {
    throw new RuntimeException("FAIL");
  }
}
