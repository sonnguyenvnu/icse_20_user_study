public void perform(Socket socket) throws IOException {
  sendRequest(socket);
  receiveResponse(socket);
}
