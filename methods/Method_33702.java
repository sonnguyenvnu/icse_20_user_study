/** 
 * ?????????
 */
public static void openLink(Context context,String content){
  Uri issuesUrl=Uri.parse(content);
  Intent intent=new Intent(Intent.ACTION_VIEW,issuesUrl);
  context.startActivity(intent);
}
