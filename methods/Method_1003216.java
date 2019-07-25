private boolean allow(Socket socket){
  if (allowOthers) {
    return true;
  }
  try {
    return NetUtils.isLocalAddress(socket);
  }
 catch (  UnknownHostException e) {
    traceError(e);
    return false;
  }
}
