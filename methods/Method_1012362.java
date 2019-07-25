public static void start(Activity activity,String from){
  Intent intent=new Intent(activity,WallPaperActivity.class);
  intent.putExtra(ConstantUtil.FROM,from);
  activity.startActivity(intent);
}
