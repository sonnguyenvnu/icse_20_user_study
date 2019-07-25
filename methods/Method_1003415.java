private static void extract(String fromFile,String toDir) throws IOException {
  Log log=new Log();
  long start=System.nanoTime();
  long startMs=System.currentTimeMillis();
  long size=new File(fromFile).length();
  log.println("Extracting " + size / MB + " MB at " + new java.sql.Time(startMs).toString());
  InputStream in=new BufferedInputStream(new FileInputStream(fromFile),1024 * 1024);
  String temp=fromFile + ".temp";
  Inflater inflater=new Inflater();
  in=new InflaterInputStream(in,inflater,1024 * 1024);
  OutputStream out=getDirectoryOutputStream(toDir);
  combine(log,in,out,temp);
  inflater.end();
  in.close();
  out.close();
  log.println();
  log.println("Extracted in " + TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - start) + " seconds");
}
