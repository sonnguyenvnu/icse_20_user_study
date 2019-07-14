public static void addKeepAliveExtra(Context context,Intent intent){
  Intent keepAliveIntent=new Intent().setClassName(context.getPackageName(),KeepAliveService.class.getCanonicalName());
  intent.putExtra(EXTRA_CUSTOM_TABS_KEEP_ALIVE,keepAliveIntent);
}
