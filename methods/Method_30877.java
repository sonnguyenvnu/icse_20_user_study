public static Intent makeIntent(String uri,String disableLoadOverridingUrl,Context context){
  return makeIntent(uri,new String[]{disableLoadOverridingUrl},context);
}
