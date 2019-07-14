public RSyntaxTextArea getCurrentTextArea(){
  RSyntaxTextArea currentTextArea=null;
  try {
    int pos=house.getSelectedIndex();
    System.out.println(pos);
    if (pos >= 0) {
      RTextScrollPane co=(RTextScrollPane)house.getComponentAt(pos);
      currentTextArea=(RSyntaxTextArea)co.getViewport().getView();
    }
  }
 catch (  Exception e1) {
    Luyten.showExceptionDialog("Exception!",e1);
  }
  if (currentTextArea == null) {
    getLabel().setText("No open tab");
  }
  return currentTextArea;
}
