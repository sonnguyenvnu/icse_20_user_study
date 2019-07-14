/** 
 * Activity ??
 * @param context
 * @param goal
 */
public static void skipActivity(Context context,Class<?> goal){
  Intent intent=new Intent(context,goal);
  context.startActivity(intent);
}
