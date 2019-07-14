/** 
 * Activity ??
 * @param context
 * @param goal
 */
public static void skipActivity(Context context,Class<?> goal,Bundle bundle){
  Intent intent=new Intent(context,goal);
  intent.putExtras(bundle);
  context.startActivity(intent);
}
