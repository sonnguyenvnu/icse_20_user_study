/** 
 * ????
 * @param payInterface ????
 * @param billDate ???
 */
public File fileDown(String payInterface,Date billDate) throws Exception {
  FileDown fileDown=(FileDown)this.getService(payInterface);
  String dir=ReconciliationConfigUtil.readConfig("dir") + payInterface.toLowerCase();
  return fileDown.fileDown(billDate,dir);
}
