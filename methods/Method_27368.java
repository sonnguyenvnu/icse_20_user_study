public static Intent start(@NonNull Context context,long id,@NonNull String url,boolean onlyRead){
  Intent intent=new Intent(context.getApplicationContext(),ReadNotificationService.class);
  intent.putExtras(Bundler.start().put(BundleConstant.EXTRA_TYPE,OPEN_NOTIFICATION).put(BundleConstant.EXTRA,url).put(BundleConstant.ID,id).put(BundleConstant.YES_NO_EXTRA,onlyRead).end());
  return intent;
}
