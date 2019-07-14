/** 
 * @return true if the text is being edited from direct input from typing andnot shortcuts that manipulate text
 */
boolean isDirectEdit(){
  return endUndoEvent != null;
}
