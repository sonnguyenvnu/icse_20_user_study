/** 
 * Dereference the underlying node if the argument node is a Proxy delegating to a NodeModificationInvocationHandler.
 * @param node the possible proxy node
 * @return the dereferenced node if it was wrapped, otherwise the original node
 */
public static Node dereference(Node node){
  if (Proxy.isProxyClass(node.getClass())) {
    InvocationHandler handler=Proxy.getInvocationHandler(node);
    if (handler instanceof NodeModificationInvocationHandler) {
      NodeModificationInvocationHandler modHandler=(NodeModificationInvocationHandler)handler;
      return modHandler.getWrappedNode();
    }
  }
  return node;
}
