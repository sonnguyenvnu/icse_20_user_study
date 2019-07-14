protected boolean promptToVisitDownloadPage(){
  String prompt=Language.text("update_check.updates_available.core");
  Object[] options={Language.text("prompt.yes"),Language.text("prompt.no")};
  int result=JOptionPane.showOptionDialog(base.activeEditor,prompt,Language.text("update_check"),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
  if (result == JOptionPane.YES_OPTION) {
    Platform.openURL(DOWNLOAD_URL);
    return true;
  }
  return false;
}
