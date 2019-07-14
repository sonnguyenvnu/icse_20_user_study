public static void openWithCustomTabs(Uri uri,CustomTabsActivityHelper.CustomTabsFallback fallback,Activity activity){
  CustomTabsHelperFragment.open(activity,makeCustomTabsIntent(activity),uri,fallback);
}
