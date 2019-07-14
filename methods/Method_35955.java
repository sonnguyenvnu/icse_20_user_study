private void writeGarbageThenCloseSocket(){
  response.getHttpOutput().getHttpChannel().getEndPoint().write(new Callback(){
    @Override public void succeeded(){
      try {
        socket.close();
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
    @Override public void failed(    Throwable x){
      try {
        socket.close();
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
  }
,BufferUtil.toBuffer(GARBAGE));
}
