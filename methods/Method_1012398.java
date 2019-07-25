public static void start(Context context,String skipID,String title){
  Intent intent=new Intent(context,SpecialNewsActivity.class);
  intent.putExtra(NewsUtil.SPECIAL_ID,skipID);
  intent.putExtra(NewsUtil.TITLE,title);
  context.startActivity(intent);
}
