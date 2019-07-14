public static Intent editBundle(@NonNull Intent intent,boolean isEnterprise){
  Bundle bundle=intent.getExtras();
  if (bundle != null) {
    bundle.putBoolean(BundleConstant.IS_ENTERPRISE,isEnterprise);
    intent.putExtras(bundle);
  }
  return intent;
}
