static void visit(Object resource,InvocationHandler visitor) throws IOException {
  Object visitorProxy=Proxy.newProxyInstance(VIRTUAL_FILE_VISITOR_INTERFACE.getClassLoader(),new Class<?>[]{VIRTUAL_FILE_VISITOR_INTERFACE},visitor);
  invokeVfsMethod(VIRTUAL_FILE_METHOD_VISIT,resource,visitorProxy);
}
