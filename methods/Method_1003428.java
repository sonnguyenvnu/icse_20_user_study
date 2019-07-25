private void reply(int code,String message){
  server.trace(code + " " + message);
  output.print(code + " " + message + "\r\n");
  output.flush();
  replied=true;
}
