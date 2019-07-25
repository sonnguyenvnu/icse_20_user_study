default Handler chain(Class<? extends Action<? super Chain>> action) throws Exception {
  return chain(getRegistry().get(action));
}
