/** 
 * Change what file is currently being edited. Changes the current tab index. <OL> <LI> store the String for the text of the current file. <LI> retrieve the String for the text of the new file. <LI> change the text that's visible in the text area </OL>
 */
public void setCurrentCode(int which){
  if (which < 0 || which >= codeCount || ((currentIndex == which) && (current == code[currentIndex]))) {
    return;
  }
  if (current != null) {
    current.setState(editor.getText(),editor.getSelectionStart(),editor.getSelectionStop(),editor.getScrollPosition());
  }
  current=code[which];
  currentIndex=which;
  current.visited=System.currentTimeMillis();
  editor.setCode(current);
  editor.repaintHeader();
}
