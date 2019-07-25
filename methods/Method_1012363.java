public static void start(Activity activity,WeatherInfoBean data){
  Intent weatherIntent=new Intent(activity,WeatherInfoActivity.class);
  weatherIntent.putExtra(ConstantUtil.DATA,data);
  activity.startActivityForResult(weatherIntent,ConstantUtil.REQUEST_CODE_WEATHER_INFO);
}
