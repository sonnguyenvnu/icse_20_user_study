public void receive(Message msg){
  byte[] buf=msg.getRawBuffer();
  if (buf == null) {
    System.err.printf("%s: received null buffer from %s, headers: %s\n",channel.getAddress(),msg.src(),msg.printHeaders());
    return;
  }
  try {
    DrawCommand comm=Util.streamableFromByteBuffer(DrawCommand::new,buf,msg.getOffset(),msg.getLength());
switch (comm.mode) {
case DrawCommand.DRAW:
      if (panel != null)       panel.drawPoint(comm);
    break;
case DrawCommand.CLEAR:
  clearPanel();
break;
default :
System.err.println("***** received invalid draw command " + comm.mode);
break;
}
}
 catch (Exception e) {
e.printStackTrace();
}
}
