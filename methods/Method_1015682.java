public void loopback(Message msg,boolean oob,boolean internal){
  if (oob || internal) {
    super.loopback(msg,oob,internal);
    return;
  }
  MessageTable table=msg.dest() == null ? mcasts : ucasts;
  table.process(msg,true);
}
