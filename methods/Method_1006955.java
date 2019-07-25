@Override public XMLEvent peek() throws XMLStreamException {
  if (fakeDocumentEnd) {
    return null;
  }
  return alterEvent(wrappedEventReader.peek(),true);
}
