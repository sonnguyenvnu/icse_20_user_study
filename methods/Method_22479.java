public void showFrame(final Editor editor,boolean error,boolean loading){
  this.editor=editor;
  setLayout(error,loading);
  contributionListPanel.setVisible(!loading);
  loaderLabel.setVisible(loading);
  errorPanel.setVisible(error);
  validate();
  repaint();
}
