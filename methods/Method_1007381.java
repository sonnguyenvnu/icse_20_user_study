final void process(Socket clnt) throws IOException {
  InputStream in=new BufferedInputStream(clnt.getInputStream());
  String cmd=readLine(in);
  logging(clnt.getInetAddress().getHostName(),new Date().toString(),cmd);
  while (skipLine(in) > 0) {
  }
  OutputStream out=new BufferedOutputStream(clnt.getOutputStream());
  try {
    doReply(in,out,cmd);
  }
 catch (  BadHttpRequest e) {
    replyError(out,e);
  }
  out.flush();
  in.close();
  out.close();
  clnt.close();
}
