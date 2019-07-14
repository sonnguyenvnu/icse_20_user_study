public static Intent makeIntent(String uri,String[] disableLoadOverridingUrls,Context context){
  return makeIntent(uri,context).putExtra(EXTRA_DISABLE_LOAD_OVERRIDING_URLS,disableLoadOverridingUrls);
}
