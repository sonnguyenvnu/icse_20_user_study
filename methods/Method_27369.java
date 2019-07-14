public static void start(@NonNull Context context,@NonNull long[] ids){
  Intent intent=new Intent(context.getApplicationContext(),ReadNotificationService.class);
  intent.putExtras(Bundler.start().put(BundleConstant.EXTRA_TYPE,READ_ALL).put(BundleConstant.ID,ids).end());
  context.startService(intent);
}
