/** 
 * Activity ??
 * @param context
 * @param goal
 */
public static void skipActivityAndFinish(Context context,Class<?> goal,Bundle bundle){
  Intent intent=new Intent(context,goal);
  intent.putExtras(bundle);
  context.startActivity(intent);
  ((Activity)context).finish();
}
