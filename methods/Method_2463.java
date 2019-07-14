@Override public IDataSet load(String folderPath,String charsetName,double percentage) throws IllegalArgumentException, IOException {
  if (folderPath == null)   throw new IllegalArgumentException("?? folderPath == null");
  File root=new File(folderPath);
  if (!root.exists())   throw new IllegalArgumentException(String.format("?? %s ???",root.getAbsolutePath()));
  if (!root.isDirectory())   throw new IllegalArgumentException(String.format("?? %s ??????",root.getAbsolutePath()));
  if (percentage > 1.0 || percentage < -1.0)   throw new IllegalArgumentException("percentage ????????[0, 1]??");
  File[] folders=root.listFiles();
  if (folders == null)   return null;
  logger.start("??:%s\n????:%s\n???:%s\n???...\n",testingDataSet ? "???" : "???",charsetName,folderPath);
  for (  File folder : folders) {
    if (folder.isFile())     continue;
    File[] files=folder.listFiles();
    if (files == null)     continue;
    String category=folder.getName();
    logger.out("[%s]...",category);
    int b, e;
    if (percentage > 0) {
      b=0;
      e=(int)(files.length * percentage);
    }
 else {
      b=(int)(files.length * (1 + percentage));
      e=files.length;
    }
    int logEvery=(int)Math.ceil((e - b) / 10000f);
    for (int i=b; i < e; i++) {
      add(folder.getName(),TextProcessUtility.readTxt(files[i],charsetName));
      if (i % logEvery == 0) {
        logger.out("%c[%s]...%.2f%%",13,category,MathUtility.percentage(i - b + 1,e - b));
      }
    }
    logger.out(" %d ???\n",e - b);
  }
  logger.finish(" ??? %d ???,? %d ???\n",getCatalog().size(),size());
  return this;
}
