private ArrayList<String> getPreferredLocales(){
  Configuration configuration=getReactApplicationContext().getResources().getConfiguration();
  ArrayList<String> preferred=new ArrayList<>();
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    for (int i=0; i < configuration.getLocales().size(); i++) {
      preferred.add(configuration.getLocales().get(i).getLanguage());
    }
  }
 else {
    preferred.add(configuration.locale.getLanguage());
  }
  return preferred;
}
