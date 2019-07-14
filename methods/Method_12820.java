private void responseClient(Socket client) throws IOException {
  BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
  PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
  out.println("????????");
  while (!mIsServiceDestoryed) {
    String str=in.readLine();
    System.out.println("msg from client:" + str);
    if (str == null) {
      break;
    }
    int i=new Random().nextInt(mDefinedMessages.length);
    String msg=mDefinedMessages[i];
    out.println(msg);
    System.out.println("send :" + msg);
  }
  System.out.println("client quit.");
  MyUtils.close(out);
  MyUtils.close(in);
  client.close();
}
