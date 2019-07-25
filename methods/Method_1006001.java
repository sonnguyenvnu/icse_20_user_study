private void main(){
  mWebSocket.onWritingThreadStarted();
  while (true) {
    int result=waitForFrames();
    if (result == SHOULD_STOP) {
      break;
    }
 else     if (result == SHOULD_FLUSH) {
      flushIgnoreError();
      continue;
    }
 else     if (result == SHOULD_CONTINUE) {
      continue;
    }
    try {
      sendFrames(false);
    }
 catch (    WebSocketException e) {
      break;
    }
  }
  try {
    sendFrames(true);
  }
 catch (  WebSocketException e) {
  }
}
