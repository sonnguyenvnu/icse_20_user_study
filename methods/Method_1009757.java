private void refresh(){
  try {
    final Context context=this.context.get();
    if (context != null) {
      final PackageInfo info=context.getPackageManager().getPackageInfo(context.getPackageName(),0);
      codeLabel.setText(String.valueOf(info.versionCode));
      nameLabel.setText(info.versionName);
      packageLabel.setText(info.packageName);
    }
  }
 catch (  PackageManager.NameNotFoundException e) {
  }
}
