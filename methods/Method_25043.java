private void handleFrameFragment(WebSocketFrame frame) throws IOException {
  if (frame.getOpCode() != OpCode.Continuation) {
    if (this.continuousOpCode != null) {
      throw new WebSocketException(CloseCode.ProtocolError,"Previous continuous frame sequence not completed.");
    }
    this.continuousOpCode=frame.getOpCode();
    this.continuousFrames.clear();
    this.continuousFrames.add(frame);
  }
 else   if (frame.isFin()) {
    if (this.continuousOpCode == null) {
      throw new WebSocketException(CloseCode.ProtocolError,"Continuous frame sequence was not started.");
    }
    this.continuousFrames.add(frame);
    onMessage(new WebSocketFrame(this.continuousOpCode,this.continuousFrames));
    this.continuousOpCode=null;
    this.continuousFrames.clear();
  }
 else   if (this.continuousOpCode == null) {
    throw new WebSocketException(CloseCode.ProtocolError,"Continuous frame sequence was not started.");
  }
 else {
    this.continuousFrames.add(frame);
  }
}
