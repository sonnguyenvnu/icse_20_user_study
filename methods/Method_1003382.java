/** 
 * Just run the spellchecker.
 */
@Description(summary="Run the spellchecker.") public void spellcheck(){
  java("org.h2.build.doc.SpellChecker",null);
}
