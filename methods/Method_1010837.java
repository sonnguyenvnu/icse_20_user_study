@Override public void update(AnActionEvent e){
  SModel model=MPSCommonDataKeys.CONTEXT_MODEL.getData(e.getDataContext());
  e.getPresentation().setVisible(model instanceof GeneratableSModel);
}
