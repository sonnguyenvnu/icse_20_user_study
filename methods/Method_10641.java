/** 
 * Activity ??
 * @param context
 * @param goal
 */
public static void skipActivityAndFinish(Context context,Class<?> goal){
  Intent intent=new Intent(context,goal);
  context.startActivity(intent);
  ((Activity)context).finish();
}
