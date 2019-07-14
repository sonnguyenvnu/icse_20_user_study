private boolean sendCommand(String cmd,@NotNull Process rubyProcess){
  try {
    OutputStreamWriter writer=new OutputStreamWriter(rubyProcess.getOutputStream());
    writer.write(cmd);
    writer.write("\n");
    writer.flush();
    return true;
  }
 catch (  Exception e) {
    _.msg("\nFailed to send command to Ruby interpreter: " + cmd);
    return false;
  }
}
