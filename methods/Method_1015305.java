public void send(final String input){
  SmartClient client=getClient();
  if (!checkClient(client)) {
    return;
  }
  String name=client.getAccount().getName();
  String msg=formatInput(name,input);
  if (!hideMyInput()) {
    insertDocument(msg);
    IMHistoryManager.getInstance().save(getHistoryDir(),getHistoryFile(),msg);
  }
  new Thread(){
    @Override public void run(){
      post(input);
    }
  }
.start();
}
