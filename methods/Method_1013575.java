@Override public void write(IValueOutputStream vos) throws IOException {
  throw new WrongInvocationException("ValueOutputStream: Can not pickle the value\n" + Values.ppr(toString()));
}
