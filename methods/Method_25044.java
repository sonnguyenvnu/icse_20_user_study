private void handleWebsocketFrame(WebSocketFrame frame) throws IOException {
  debugFrameReceived(frame);
  if (frame.getOpCode() == OpCode.Close) {
    handleCloseFrame(frame);
  }
 else   if (frame.getOpCode() == OpCode.Ping) {
    sendFrame(new WebSocketFrame(OpCode.Pong,true,frame.getBinaryPayload()));
  }
 else   if (frame.getOpCode() == OpCode.Pong) {
    onPong(frame);
  }
 else   if (!frame.isFin() || frame.getOpCode() == OpCode.Continuation) {
    handleFrameFragment(frame);
  }
 else   if (this.continuousOpCode != null) {
    throw new WebSocketException(CloseCode.ProtocolError,"Continuous frame sequence not completed.");
  }
 else   if (frame.getOpCode() == OpCode.Text || frame.getOpCode() == OpCode.Binary) {
    onMessage(frame);
  }
 else {
    throw new WebSocketException(CloseCode.ProtocolError,"Non control or continuous frame expected.");
  }
}
