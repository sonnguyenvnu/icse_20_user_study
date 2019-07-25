/** 
 * Run matcher on input text.
 * @param text Input text.
 * @return Success of match.
 */
public boolean run(String text){
  m.setTarget(text);
  return m.find();
}
