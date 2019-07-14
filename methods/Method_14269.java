/** 
 * languagePrefix will be stored in the meta model as an identifier.  so be careful when change it as it will break the backward compatibility for the old project 
 * @param languagePrefix
 * @param name
 * @param parser
 * @param defaultExpression
 */
static public void registerLanguageParser(String languagePrefix,String name,LanguageSpecificParser parser,String defaultExpression){
  s_languages.put(languagePrefix,new LanguageInfo(name,parser,defaultExpression));
}
