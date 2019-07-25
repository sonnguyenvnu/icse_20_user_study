void start(String props,String channel_name) throws Exception {
  ch=new JChannel(props);
  ch.setName(channel_name);
  ch.setReceiver(new ReceiverAdapter(){
    public void viewAccepted(    View view){
      System.out.println("-- view: " + view);
    }
  }
);
  loop();
}
