static public int showYesNoQuestion(Frame editor,String title,String primary,String secondary){
  if (!Platform.isMacOS()) {
    return JOptionPane.showConfirmDialog(editor,"<html><body>" + "<b>" + primary + "</b>" + "<br>" + secondary,title,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
  }
 else {
    int result=showCustomQuestion(editor,title,primary,secondary,0,"Yes","No");
    if (result == 0) {
      return JOptionPane.YES_OPTION;
    }
 else     if (result == 1) {
      return JOptionPane.NO_OPTION;
    }
 else {
      return JOptionPane.CLOSED_OPTION;
    }
  }
}
