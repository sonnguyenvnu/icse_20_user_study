/** 
 * ??App????
 * @param dirs ??
 * @return {@code true}: ??<br> {@code false}: ??
 */
public static boolean cleanAppData(Context context,File... dirs){
  boolean isSuccess=RxFileTool.cleanInternalCache(context);
  isSuccess&=RxFileTool.cleanInternalDbs(context);
  isSuccess&=RxFileTool.cleanInternalSP(context);
  isSuccess&=RxFileTool.cleanInternalFiles(context);
  isSuccess&=RxFileTool.cleanExternalCache(context);
  for (  File dir : dirs) {
    isSuccess&=RxFileTool.cleanCustomCache(dir);
  }
  return isSuccess;
}
