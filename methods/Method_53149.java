private Object readResolve() throws ObjectStreamException {
  return SINGLETON;
}
