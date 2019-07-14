/** 
 * Set TextLayout to the painter. TextLayout will be created and set by CompositionTextManager.
 * @see CompositionTextManager
 */
public void setComposedTextLayout(TextLayout composedTextLayout,int composedStartCaretPosition){
  this.composedTextLayout=composedTextLayout;
  this.composedBeginCaretPosition=composedStartCaretPosition;
}
