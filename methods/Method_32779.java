private String getCurrentLanguage(){
  Locale current;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    current=getReactApplicationContext().getResources().getConfiguration().getLocales().get(0);
  }
 else {
    current=getReactApplicationContext().getResources().getConfiguration().locale;
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    return current.toLanguageTag();
  }
 else {
    StringBuilder builder=new StringBuilder();
    builder.append(current.getLanguage());
    if (current.getCountry() != null) {
      builder.append("-");
      builder.append(current.getCountry());
    }
    return builder.toString();
  }
}
