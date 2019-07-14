private void handleCloseFrame(WebSocketFrame frame) throws IOException {
  CloseCode code=CloseCode.NormalClosure;
  String reason="";
  if (frame instanceof CloseFrame) {
    code=((CloseFrame)frame).getCloseCode();
    reason=((CloseFrame)frame).getCloseReason();
  }
  if (this.state == State.CLOSING) {
    doClose(code,reason,false);
  }
 else {
    close(code,reason,true);
  }
}
