/** 
 * Override this to provide your own painter for this  {@link JEditTextArea}.
 * @param defaults
 * @return a newly constructed {@link TextAreaPainter}.
 */
protected TextAreaPainter createPainter(final TextAreaDefaults defaults){
  return new TextAreaPainter(this,defaults);
}
