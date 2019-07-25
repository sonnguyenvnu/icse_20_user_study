public static void start(Activity activity,String url,String title){
  Intent intent=new Intent(activity,WebActivity.class);
  intent.putExtra(Constant.TITLE,title);
  intent.putExtra(Constant.DATA,url);
  activity.startActivity(intent);
}
