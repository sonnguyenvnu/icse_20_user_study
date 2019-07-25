/** 
 * ??drawable????id
 * @param resName ????
 * @return ??id
 */
public static int drawable(Context context,String resName){
  return context.getResources().getIdentifier(resName,"drawable",context.getPackageName());
}
