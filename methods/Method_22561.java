/** 
 * Is this a CJK language where Input Method support is suggested/required?
 * @return true if the user is running in Japanese, Korean, or Chinese
 */
static public boolean useInputMethod(){
  final String language=getLanguage();
  return (language.equals("ja") || language.equals("ko") || language.equals("zh"));
}
