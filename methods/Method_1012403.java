public static void start(Activity activity,CommonVideoBean commonVideoBean){
  Intent intent=new Intent(activity,QQVideoDetailActivity.class);
  intent.putExtra(VideoUtil.DATA,commonVideoBean);
  activity.startActivity(intent);
}
