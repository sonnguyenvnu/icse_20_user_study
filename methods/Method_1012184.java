/** 
 * ??raw????id
 * @param resName ????
 * @return ??id
 */
public static int raw(Context context,String resName){
  return context.getResources().getIdentifier(resName,"raw",context.getPackageName());
}
