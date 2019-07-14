/** 
 * ???????? .
 * @param interfaceCode ????
 * @param billDate ???
 * @return
 */
public File downReconciliationFile(String interfaceCode,Date billDate){
  if (StringUtil.isEmpty(interfaceCode)) {
    LOG.info("????????");
    return null;
  }
  return this.downFile(interfaceCode,billDate);
}
