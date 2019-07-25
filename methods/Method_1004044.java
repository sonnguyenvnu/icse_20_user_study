public void init() throws IOException {
  this.writer=new RemoteControlWriter(socket.getOutputStream());
  this.reader=new RemoteControlReader(socket.getInputStream());
  this.reader.setRemoteCommandVisitor(this);
  this.initialized=true;
}
