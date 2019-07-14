/** 
 * Fills the all language labels.
 * @param dataModel the specified data model
 */
private void fillLangs(final Map<String,Object> dataModel){
  Stopwatchs.start("Fills lang");
  try {
    dataModel.putAll(langPropsService.getAll(Locales.getLocale()));
  }
  finally {
    Stopwatchs.end();
  }
}
