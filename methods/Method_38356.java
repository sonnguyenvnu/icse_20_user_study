/** 
 * Registers a hint.
 */
public void registerHint(final String hint){
  if (hints == null) {
    hints=new ArrayList<>(hintCount);
  }
  hints.add(hint);
}
