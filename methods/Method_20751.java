/** 
 * Clears all activities from the task stack except discovery.
 */
public static void resumeDiscoveryActivity(final @NonNull Context context){
  final Intent intent=new Intent(context,DiscoveryActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  context.startActivity(intent);
}
