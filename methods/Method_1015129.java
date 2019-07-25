private void read() throws IOException {
  buffer.compact();
  remaining-=channel.read(buffer);
  buffer.flip();
}
