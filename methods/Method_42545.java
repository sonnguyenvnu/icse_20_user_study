/** 
 * ?????
 * @param billDate ???
 * @param dir ??????
 */
public File fileDown(Date billDate,String dir) throws IOException {
  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
  bill_date=sdf.format(billDate);
  HttpResponse response=null;
  try {
    String xml=this.generateXml();
    LOG.info(xml);
    response=HttpClientUtil.httpsRequest(url,"POST",xml);
    File file=new File(dir,bill_date + "_" + bill_type.toLowerCase() + ".txt");
    int index=1;
    while (file.exists()) {
      file=new File(dir,bill_date + "_" + bill_type.toLowerCase() + index + ".txt");
      index++;
    }
    return FileUtils.saveFile(response,file);
  }
 catch (  IOException e) {
    throw new IOException("????????",e);
  }
 finally {
    try {
      if (response != null) {
        response.close();
      }
    }
 catch (    IOException e) {
      LOG.error("????????/????",e);
    }
  }
}
