/** 
 * ??
 * @param sSourceFilename ???????
 * @param sResultFilename ???????
 * @param bPOStagged 1??????0??????
 * @return
 */
public static double NLPIR_FileProcess(String sSourceFilename,String sResultFilename,int bPOStagged){
  return NlpirLib.Instance.NLPIR_FileProcess(sSourceFilename,sResultFilename,bPOStagged);
}
