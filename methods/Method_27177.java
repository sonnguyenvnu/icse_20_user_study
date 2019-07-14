@SuppressWarnings("deprecation") private static void updateResourcesLegacy(Context context,String language){
  Locale locale=getLocale(language);
  Locale.setDefault(locale);
  Resources resources=context.getResources();
  Configuration configuration=resources.getConfiguration();
  configuration.locale=locale;
  resources.updateConfiguration(configuration,resources.getDisplayMetrics());
}
