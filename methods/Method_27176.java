private static void updateResources(Context context,String language){
  Locale locale=getLocale(language);
  Locale.setDefault(locale);
  Configuration configuration=context.getResources().getConfiguration();
  configuration.setLocale(locale);
  context.createConfigurationContext(configuration);
}
