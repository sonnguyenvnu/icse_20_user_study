/** 
 * ?????????? id <p> ?????????????,??????ID??? <p> ?? getResources().getIdentifier("ic_launcher", "drawable", getPackageName());
 * @param context
 * @param name
 * @param defType
 * @return
 */
public final static int getResIdByName(Context context,String name,String defType){
  return context.getResources().getIdentifier(name,defType,context.getPackageName());
}
