@Override public Operation store(AsciiString sessionId,ByteBuf sessionData){
  return Promise.<Boolean>async(d -> connection.set(sessionId,sessionData).handleAsync((value,failure) -> {
    if (failure == null) {
      if (value != null && value.equalsIgnoreCase("OK")) {
        d.success(true);
      }
 else {
        d.error(new RuntimeException("Failed to set session data"));
      }
    }
 else {
      d.error(new RuntimeException("Failed to set session data.",failure));
    }
    return null;
  }
,Execution.current().getEventLoop())).operation();
}
