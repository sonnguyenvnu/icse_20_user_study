/** 
 * Invalidate this TextLayout to set null. If a user end input via InputMethod, this method will called from CompositionTextManager.endCompositionText
 */
public void invalidateComposedTextLayout(int composedEndCaretPosition){
  this.composedTextLayout=null;
  this.composedBeginCaretPosition=composedEndCaretPosition;
}
