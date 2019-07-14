private void bindNodeToController(Node node,Class<?> controllerClass,Flow flow,FlowHandler flowHandler){
  flow.withGlobalLink(node.getId(),controllerClass);
}
