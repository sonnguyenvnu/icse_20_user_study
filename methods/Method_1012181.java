/** 
 * ??layout????id
 * @param resName ????
 * @return ??id
 */
public static int layout(Context context,String resName){
  return context.getResources().getIdentifier(resName,"layout",context.getPackageName());
}
