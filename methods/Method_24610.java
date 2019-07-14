/** 
 * {@link DocumentListener} callback. Called when text is inserted.
 */
@Override public void insertUpdate(DocumentEvent de){
  editEvent(de);
}
