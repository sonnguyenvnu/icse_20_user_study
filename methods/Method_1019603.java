/** 
 * Overridden to remember the language index we're leaving.
 */
@Override protected void yybegin(int state,int languageIndex){
  phpInLangIndex=getLanguageIndex();
  yybegin(state);
  setLanguageIndex(languageIndex);
}
