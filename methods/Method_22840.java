/** 
 * Get CompositionTextPainter, creating one if it doesn't exist.
 */
public CompositionTextPainter getCompositionTextpainter(){
  if (compositionTextPainter == null) {
    compositionTextPainter=new CompositionTextPainter(textArea);
  }
  return compositionTextPainter;
}
