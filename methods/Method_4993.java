private static StreamKey readKey(String type,int version,DataInputStream input) throws IOException {
  int periodIndex;
  int groupIndex;
  int trackIndex;
  if ((TYPE_HLS.equals(type) || TYPE_SS.equals(type)) && version == 0) {
    periodIndex=0;
    groupIndex=input.readInt();
    trackIndex=input.readInt();
  }
 else {
    periodIndex=input.readInt();
    groupIndex=input.readInt();
    trackIndex=input.readInt();
  }
  return new StreamKey(periodIndex,groupIndex,trackIndex);
}
