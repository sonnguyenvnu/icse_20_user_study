@Override public SequenceIterator getTypedValue() throws XPathException {
  return atomize().iterate();
}
