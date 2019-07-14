/** 
 * ??????fail?
 * @param file ????
 * @param batch ??????
 */
public void isError(File file,RpAccountCheckBatch batch){
  try {
    String content=FileUtils.readFileToString(file,"UTF-8");
    if (content.contains("<return_code>")) {
      Map<String,Object> map=XmlUtils.xmlToMap(content);
      String returnMsg=map.get("return_msg") != null ? map.get("return_msg").toString() : "";
      batch.setBankErrMsg(returnMsg);
      if (returnMsg.contains("No Bill Exist")) {
        batch.setStatus(BatchStatusEnum.NOBILL.name());
      }
 else {
        batch.setStatus(BatchStatusEnum.ERROR.name());
        rpAccountCheckBatchService.saveData(batch);
      }
    }
  }
 catch (  DocumentException e) {
    LOG.error("??????(????????)??",e);
  }
catch (  IOException e) {
    LOG.error("??????(????????)??",e);
  }
}
