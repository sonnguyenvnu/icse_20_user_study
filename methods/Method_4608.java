/** 
 * Returns a  {@link VbriSeeker} for seeking in the stream, if required information is present.Returns  {@code null} if not. On returning, {@code frame}'s position is not specified so the caller should reset it.
 * @param inputLength The length of the stream in bytes, or {@link C#LENGTH_UNSET} if unknown.
 * @param position The position of the start of this frame in the stream.
 * @param mpegAudioHeader The MPEG audio header associated with the frame.
 * @param frame The data in this audio frame, with its position set to immediately after the'VBRI' tag.
 * @return A {@link VbriSeeker} for seeking in the stream, or {@code null} if the requiredinformation is not present.
 */
public static @Nullable VbriSeeker create(long inputLength,long position,MpegAudioHeader mpegAudioHeader,ParsableByteArray frame){
  frame.skipBytes(10);
  int numFrames=frame.readInt();
  if (numFrames <= 0) {
    return null;
  }
  int sampleRate=mpegAudioHeader.sampleRate;
  long durationUs=Util.scaleLargeTimestamp(numFrames,C.MICROS_PER_SECOND * (sampleRate >= 32000 ? 1152 : 576),sampleRate);
  int entryCount=frame.readUnsignedShort();
  int scale=frame.readUnsignedShort();
  int entrySize=frame.readUnsignedShort();
  frame.skipBytes(2);
  long minPosition=position + mpegAudioHeader.frameSize;
  long[] timesUs=new long[entryCount];
  long[] positions=new long[entryCount];
  for (int index=0; index < entryCount; index++) {
    timesUs[index]=(index * durationUs) / entryCount;
    positions[index]=Math.max(position,minPosition);
    int segmentSize;
switch (entrySize) {
case 1:
      segmentSize=frame.readUnsignedByte();
    break;
case 2:
  segmentSize=frame.readUnsignedShort();
break;
case 3:
segmentSize=frame.readUnsignedInt24();
break;
case 4:
segmentSize=frame.readUnsignedIntToInt();
break;
default :
return null;
}
position+=segmentSize * scale;
}
if (inputLength != C.LENGTH_UNSET && inputLength != position) {
Log.w(TAG,"VBRI data size mismatch: " + inputLength + ", " + position);
}
return new VbriSeeker(timesUs,positions,durationUs,position);
}
