protected void authorise(final Operation operation,final User user){
  if (null != operation) {
    if (operation instanceof Operations) {
      final Collection<? extends Operation> operations=((Operations<?>)operation).getOperations();
      operations.forEach(op -> authorise(op,user));
    }
    final Class<? extends Operation> opClass=operation.getClass();
    final Set<String> userOpAuths=user.getOpAuths();
    boolean authorised=true;
    for (    final Entry<Class<?>,Set<String>> entry : auths.entrySet()) {
      if ((entry.getKey().isAssignableFrom(opClass)) && (!userOpAuths.containsAll(entry.getValue()))) {
        authorised=false;
        break;
      }
    }
    if (!authorised) {
      throw new UnauthorisedException("User does not have permission to run operation: " + operation.getClass().getName());
    }
  }
}
