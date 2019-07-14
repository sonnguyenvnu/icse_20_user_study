private void bindComponentToContent(Component component,Object content){
  component.bind(getContextForComponent(component),content);
  mDynamicPropsManager.onBindComponentToContent(component,content);
}
