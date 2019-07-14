protected Object readResolve() throws ObjectStreamException {
  return getInstance(this);
}
