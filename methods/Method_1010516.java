/** 
 * Process language reference dependency
 * @param lang   reference to language module, never <code>null</code>. Language it points to not necessarily resolves
 * @param retval collection to fill with languages of interest
 */
protected void handle(SLanguage lang,Collection<SLanguage> retval){
  retval.add(lang);
}
