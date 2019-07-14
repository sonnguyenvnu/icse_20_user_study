/** 
 * Deletes the selected text from the text area and places it into the clipboard.
 */
public void cut(){
  if (editable) {
    copy();
    setSelectedText("");
  }
}
