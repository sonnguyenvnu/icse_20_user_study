/** 
 * @param which either yes, no, cancel, ok, or browse
 */
static public String getPrompt(String which){
  return Language.text("prompt." + which);
}
