/** 
 * Starts a new JFlex state and changes the current language index.
 * @param state The new JFlex state to enter.
 * @param languageIndex The new language index.
 */
protected void yybegin(int state,int languageIndex){
  yybegin(state);
  setLanguageIndex(languageIndex);
}
