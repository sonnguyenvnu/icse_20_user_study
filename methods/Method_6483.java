public void init(){
  if (initied) {
    return;
  }
  dateFormat=FastDateFormat.getInstance("dd_MM_yyyy_HH_mm_ss",Locale.US);
  try {
    File sdCard=ApplicationLoader.applicationContext.getExternalFilesDir(null);
    if (sdCard == null) {
      return;
    }
    File dir=new File(sdCard.getAbsolutePath() + "/logs");
    dir.mkdirs();
    currentFile=new File(dir,dateFormat.format(System.currentTimeMillis()) + ".txt");
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  try {
    logQueue=new DispatchQueue("logQueue");
    currentFile.createNewFile();
    FileOutputStream stream=new FileOutputStream(currentFile);
    streamWriter=new OutputStreamWriter(stream);
    streamWriter.write("-----start log " + dateFormat.format(System.currentTimeMillis()) + "-----\n");
    streamWriter.flush();
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  initied=true;
}
