public Timing withResponseSendTime(int responseSendTimeMillis){
  return new Timing(addedDelay,processTime,responseSendTimeMillis,-1,-1);
}
