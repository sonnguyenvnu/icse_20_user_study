/** 
 * ????API?11 Activity ?? ???Finish?????Activity
 * @param context
 * @param goal
 */
public static void skipActivityAndFinishAll(Context context,Class<?> goal){
  Intent intent=new Intent(context,goal);
  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
  context.startActivity(intent);
  ((Activity)context).finish();
}
