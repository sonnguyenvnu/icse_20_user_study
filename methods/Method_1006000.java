private static List<WebSocketFrame> split(WebSocketFrame frame,int maxPayloadSize){
  byte[] originalPayload=frame.getPayload();
  boolean originalFin=frame.getFin();
  List<WebSocketFrame> frames=new ArrayList<WebSocketFrame>();
  byte[] payload=Arrays.copyOf(originalPayload,maxPayloadSize);
  frame.setFin(false).setPayload(payload);
  frames.add(frame);
  for (int from=maxPayloadSize; from < originalPayload.length; from+=maxPayloadSize) {
    int to=Math.min(from + maxPayloadSize,originalPayload.length);
    payload=Arrays.copyOfRange(originalPayload,from,to);
    WebSocketFrame cont=WebSocketFrame.createContinuationFrame(payload);
    frames.add(cont);
  }
  if (originalFin) {
    frames.get(frames.size() - 1).setFin(true);
  }
  return frames;
}
