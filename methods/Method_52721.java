private static <T extends AstNode>void register(Class<T> nodeType,Class<? extends EcmascriptNode<T>> nodeAdapterType){
  try {
    NODE_TYPE_TO_NODE_ADAPTER_TYPE.put(nodeType,nodeAdapterType.getConstructor(nodeType));
  }
 catch (  SecurityException|NoSuchMethodException e) {
    throw new RuntimeException(e);
  }
}
