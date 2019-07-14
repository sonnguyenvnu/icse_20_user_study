private void readWebsocket(){
  try {
    while (this.state == State.OPEN) {
      handleWebsocketFrame(WebSocketFrame.read(this.in));
    }
  }
 catch (  CharacterCodingException e) {
    onException(e);
    doClose(CloseCode.InvalidFramePayloadData,e.toString(),false);
  }
catch (  IOException e) {
    onException(e);
    if (e instanceof WebSocketException) {
      doClose(((WebSocketException)e).getCode(),((WebSocketException)e).getReason(),false);
    }
  }
 finally {
    doClose(CloseCode.InternalServerError,"Handler terminated without closing the connection.",false);
  }
}
