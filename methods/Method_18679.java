@Override protected JPanel createParametersPanel(boolean hasTabsInDialog){
  super.createParametersPanel(hasTabsInDialog);
  return ToolbarDecorator.createDecorator(myParametersList.getTable()).createPanel();
}
