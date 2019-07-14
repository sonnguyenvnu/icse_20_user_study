private String getCurrentCountry(){
  Locale current;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    current=getReactApplicationContext().getResources().getConfiguration().getLocales().get(0);
  }
 else {
    current=getReactApplicationContext().getResources().getConfiguration().locale;
  }
  return current.getCountry();
}
