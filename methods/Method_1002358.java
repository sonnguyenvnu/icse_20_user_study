public <T>Invoker<T> select(List<Invoker<T>> invokers,URL url,Invocation invocation) throws RpcException {
  if (invokers == null || invokers.isEmpty()) {
    throw new RpcException("No invoker is found!");
  }
  int lengthOfInvokerList=invokers == null ? 0 : invokers.size();
  return invokers.get(random.nextInt(lengthOfInvokerList));
}
