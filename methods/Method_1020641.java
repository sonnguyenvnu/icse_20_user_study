public void save(String path){
  Format forMat=Format.getPrettyFormat();
  forMat.setEncoding("GBK");
  forMat.setTextMode(Format.TextMode.TRIM_FULL_WHITE);
  XMLOutputter out=new XMLOutputter(forMat);
  try {
    File fl=new File(path);
    if (!fl.exists()) {
      fl.createNewFile();
    }
    out.output(doc,new FileOutputStream(path));
  }
 catch (  IOException e) {
    logger.error(e);
  }
}
