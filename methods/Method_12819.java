private void connectTCPServer(){
  Socket socket=null;
  while (socket == null) {
    try {
      socket=new Socket("localhost",8688);
      mClientSocket=socket;
      mPrintWriter=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
      mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
      System.out.println("connect server success");
    }
 catch (    IOException e) {
      SystemClock.sleep(1000);
      System.out.println("connect tcp server failed, retry...");
    }
  }
  try {
    BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    while (!TCPClientActivity.this.isFinishing()) {
      String msg=br.readLine();
      System.out.println("receive :" + msg);
      if (msg != null) {
        String time=formatDateTime(System.currentTimeMillis());
        final String showedMsg="server " + time + ":" + msg + "\n";
        mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG,showedMsg).sendToTarget();
      }
    }
    System.out.println("quit...");
    MyUtils.close(mPrintWriter);
    MyUtils.close(br);
    socket.close();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
