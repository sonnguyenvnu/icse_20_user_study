private void init(){
  if (fileName == null) {
    throw new IllegalStateException("setLogfile() must be called before init()");
  }
  try {
    printStream=new PrintStream(new FileOutputStream(fileName,P6SpyOptions.getActiveInstance().getAppend()));
  }
 catch (  IOException e) {
    e.printStackTrace(System.err);
  }
}
