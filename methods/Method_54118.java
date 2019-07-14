/** 
 * Unsafe version of:  {@link #aiExportScene ExportScene} 
 */
public static int naiExportScene(long pScene,long pFormatId,long pFileName,int pPreProcessing){
  long __functionAddress=Functions.ExportScene;
  if (CHECKS) {
    AIScene.validate(pScene);
  }
  return invokePPPI(pScene,pFormatId,pFileName,pPreProcessing,__functionAddress);
}
