@Override public void close() throws IOException {
  final int openTabState=getModel().getOpenTabsValue();
  getModelEditor().updateOpenTabsState(openTabState & ~IModelConfigurationConstants.EDITOR_OPEN_TAB_ADVANCED_MODEL);
}
