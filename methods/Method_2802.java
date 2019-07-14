/** 
 * ??
 * @param handler ????
 * @param size ???????
 * @throws Exception
 */
public void read(LineHandler handler,int size) throws Exception {
  File rootFile=new File(root);
  File[] files;
  if (rootFile.isDirectory()) {
    files=rootFile.listFiles(new FileFilter(){
      @Override public boolean accept(      File pathname){
        return pathname.isFile() && !pathname.getName().endsWith(".bin");
      }
    }
);
    if (files == null) {
      if (rootFile.isFile())       files=new File[]{rootFile};
 else       return;
    }
  }
 else {
    files=new File[]{rootFile};
  }
  int n=0;
  int totalAddress=0;
  long start=System.currentTimeMillis();
  for (  File file : files) {
    if (size-- == 0)     break;
    if (file.isDirectory())     continue;
    if (verbose)     System.out.printf("????%s, %d / %d\n",file.getName(),++n,files.length);
    IOUtil.LineIterator lineIterator=new IOUtil.LineIterator(file.getAbsolutePath());
    while (lineIterator.hasNext()) {
      ++totalAddress;
      String line=lineIterator.next();
      if (line.length() == 0)       continue;
      handler.handle(line);
    }
  }
  handler.done();
  if (verbose)   System.out.printf("??? %.2f ?????? %.2f min\n",totalAddress / 10000.0,(System.currentTimeMillis() - start) / 1000.0 / 60.0);
}
