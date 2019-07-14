static <T extends AstNode>EcmascriptNode<T> createNodeAdapter(T node){
  try {
    @SuppressWarnings("unchecked") Constructor<? extends EcmascriptNode<T>> constructor=(Constructor<? extends EcmascriptNode<T>>)NODE_TYPE_TO_NODE_ADAPTER_TYPE.get(node.getClass());
    if (constructor == null) {
      throw new IllegalArgumentException("There is no Node adapter class registered for the Node class: " + node.getClass());
    }
    return constructor.newInstance(node);
  }
 catch (  InstantiationException|IllegalAccessException e) {
    throw new RuntimeException(e);
  }
catch (  InvocationTargetException e) {
    throw new RuntimeException(e.getTargetException());
  }
}
