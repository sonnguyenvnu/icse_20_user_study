/** 
 * Process devkit dependency
 * @param devkit reference to devkit, not <code>null</code>.
 * @param retval collection to fill with languages of interest
 */
protected void handle(DevKit devkit,Collection<SLanguage> retval){
  for (  SLanguage dkLang : devkit.getAllExportedLanguageIds()) {
    handle(dkLang,retval);
  }
}
