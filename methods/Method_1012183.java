/** 
 * ??string????id
 * @param resName ????
 * @return ??id
 */
public static int string(Context context,String resName){
  return context.getResources().getIdentifier(resName,"string",context.getPackageName());
}
