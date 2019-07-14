/** 
 * @param highlight A valid array index for options[] that specifies thedefault (i.e. safe) choice.
 * @return The (zero-based) index of the selected value, -1 otherwise.
 */
static public int showCustomQuestion(Frame editor,String title,String primary,String secondary,int highlight,String... options){
  Object result;
  if (!Platform.isMacOS()) {
    return JOptionPane.showOptionDialog(editor,"<html><body><b>" + primary + "</b><br>" + secondary,title,JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[highlight]);
  }
 else {
    JOptionPane pane=new JOptionPane("<html> " + "<head> <style type=\"text/css\">" + "b { font: 13pt \"Lucida Grande\" }" + "p { font: 11pt \"Lucida Grande\"; margin-top: 8px; width: 300px }" + "</style> </head>" + "<b>" + primary + "</b>" + "<p>" + secondary,JOptionPane.QUESTION_MESSAGE);
    pane.setOptions(options);
    pane.setInitialValue(options[highlight]);
    JDialog dialog=pane.createDialog(editor,null);
    dialog.setVisible(true);
    result=pane.getValue();
  }
  for (int i=0; i < options.length; i++) {
    if (result != null && result.equals(options[i]))     return i;
  }
  return -1;
}
