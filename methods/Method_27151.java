public static void startCustomTab(@NonNull Activity context,@NonNull Uri url){
  String packageNameToUse=CustomTabsHelper.getPackageNameToUse(context);
  if (packageNameToUse != null) {
    CustomTabsIntent customTabsIntent=new CustomTabsIntent.Builder().setToolbarColor(ViewHelper.getPrimaryColor(context)).setShowTitle(true).build();
    customTabsIntent.intent.setPackage(packageNameToUse);
    try {
      customTabsIntent.launchUrl(context,url);
    }
 catch (    ActivityNotFoundException ignored) {
      openChooser(context,url,true);
    }
  }
 else {
    openChooser(context,url,true);
  }
}
