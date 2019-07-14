/** 
 * Opens the play store native app or the play store web site.
 */
public static void openStoreRating(final @NonNull Context context,final @NonNull String packageName){
  final Intent intent=new Intent(Intent.ACTION_VIEW);
  try {
    final Uri marketUri=Uri.parse("market://details?id=" + packageName);
    intent.setData(marketUri);
    context.startActivity(intent);
  }
 catch (  ActivityNotFoundException __) {
    final Uri httpUri=Uri.parse("http://play.google.com/store/apps/details?id=" + packageName);
    intent.setData(httpUri);
    context.startActivity(intent);
  }
}
