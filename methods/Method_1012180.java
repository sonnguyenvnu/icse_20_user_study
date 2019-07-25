/** 
 * ??anim????id
 * @param resName ????
 * @return ??id
 */
public static int anim(Context context,String resName){
  return context.getResources().getIdentifier(resName,"anim",context.getPackageName());
}
