public Message receive() throws IOException {
  return Message.parseDelimitedFrom(reader);
}
