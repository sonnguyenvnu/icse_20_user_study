static public void startup(){
  if (systemOut != null) {
    new Exception("startup() called more than once").printStackTrace(systemErr);
    return;
  }
  systemOut=System.out;
  systemErr=System.err;
  try {
    SimpleDateFormat formatter=new SimpleDateFormat("yyMMdd_HHmmss");
    final String stamp=formatter.format(new Date());
    File consoleDir=Base.getSettingsFile("console");
    if (consoleDir.exists()) {
      File[] stdFiles=consoleDir.listFiles(new FileFilter(){
        public boolean accept(        File file){
          if (!file.isDirectory()) {
            String name=file.getName();
            if (name.endsWith(".err") || name.endsWith(".out")) {
              return !name.startsWith(todayPrefix);
            }
          }
          return false;
        }
      }
);
      for (      File file : stdFiles) {
        file.delete();
      }
    }
 else {
      consoleDir.mkdirs();
      consoleDir.setWritable(true,false);
    }
    File outFile=new File(consoleDir,stamp + ".out");
    outFile.setWritable(true,false);
    stdoutFile=new FileOutputStream(outFile);
    File errFile=new File(consoleDir,stamp + ".err");
    errFile.setWritable(true,false);
    stderrFile=new FileOutputStream(errFile);
    consoleOut=new PrintStream(new ConsoleStream(false));
    consoleErr=new PrintStream(new ConsoleStream(true));
    System.setOut(consoleOut);
    System.setErr(consoleErr);
  }
 catch (  Exception e) {
    stdoutFile=null;
    stderrFile=null;
    consoleOut=null;
    consoleErr=null;
    System.setOut(systemOut);
    System.setErr(systemErr);
    e.printStackTrace();
  }
}
