/** 
 * ??file??
 * @param batch ??????
 * @param file ???????
 * @param billDate ????????
 * @param interfaceCode ???????
 * @return ?????vo??
 * @throws IOException
 */
public List<ReconciliationEntityVo> parser(RpAccountCheckBatch batch,File file,Date billDate,String interfaceCode) throws IOException {
  List<ReconciliationEntityVo> rcVoList=null;
  String parserClassName=interfaceCode + "Parser";
  LOG.info("??????????????[" + parserClassName + "]");
  ParserInterface service=null;
  try {
    service=(ParserInterface)this.getService(parserClassName);
  }
 catch (  NoSuchBeanDefinitionException e) {
    LOG.error("????????[" + parserClassName + "]???????????");
    return null;
  }
  rcVoList=service.parser(file,billDate,batch);
  return rcVoList;
}
