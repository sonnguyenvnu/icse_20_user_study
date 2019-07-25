private void connect(String url) throws IOException {
  socket=NetUtils.createSocket(url,21,false);
  InputStream in=socket.getInputStream();
  OutputStream out=socket.getOutputStream();
  reader=new BufferedReader(new InputStreamReader(in));
  writer=new PrintWriter(new OutputStreamWriter(out,StandardCharsets.UTF_8));
  readCode(220);
}
