private ShellConnection connect() throws IOException {
  final Socket socket=new Socket();
  socket.connect(address);
  return new ShellConnection(socket);
}
