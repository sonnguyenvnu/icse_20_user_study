public void fill(EndPoint endpoint,int offset,int size) throws IOException, Pausable {
  int total=offset + size;
  int currentPos=buffer.position();
  if (total > buffer.position()) {
    buffer=endpoint.fill(buffer,(total - currentPos));
  }
}
