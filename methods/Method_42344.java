/** 
 * ????
 * @param interfaceCode ????
 * @param tradeGainCheckFileTime ???????????
 */
private File downFile(String interfaceCode,Date billDate){
  LOG.info("??????[" + interfaceCode + "],????????????>>>");
  try {
    File file=null;
    int downloadTrytimes=0;
    while (file == null && downloadTrytimes < DOWNLOAD_TRY_TIMES) {
      try {
        downloadTrytimes++;
        file=reconciliationFactory.fileDown(interfaceCode,billDate);
      }
 catch (      Exception e) {
        LOG.error("????????",e);
        Thread.sleep(10000);
      }
    }
    return file;
  }
 catch (  Exception e) {
    LOG.error("??????????",e);
  }
  return null;
}
