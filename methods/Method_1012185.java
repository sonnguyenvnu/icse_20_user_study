/** 
 * ??style????id
 * @param resName ????
 * @return ??id
 */
public static int style(Context context,String resName){
  return context.getResources().getIdentifier(resName,"style",context.getPackageName());
}
