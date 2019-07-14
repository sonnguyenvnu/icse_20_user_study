/** 
 * Returns an  {@link MlltSeeker} for seeking in the stream.
 * @param firstFramePosition The position of the start of the first frame in the stream.
 * @param mlltFrame The MLLT frame with seeking metadata.
 * @return An {@link MlltSeeker} for seeking in the stream.
 */
public static MlltSeeker create(long firstFramePosition,MlltFrame mlltFrame){
  int referenceCount=mlltFrame.bytesDeviations.length;
  long[] referencePositions=new long[1 + referenceCount];
  long[] referenceTimesMs=new long[1 + referenceCount];
  referencePositions[0]=firstFramePosition;
  referenceTimesMs[0]=0;
  long position=firstFramePosition;
  long timeMs=0;
  for (int i=1; i <= referenceCount; i++) {
    position+=mlltFrame.bytesBetweenReference + mlltFrame.bytesDeviations[i - 1];
    timeMs+=mlltFrame.millisecondsBetweenReference + mlltFrame.millisecondsDeviations[i - 1];
    referencePositions[i]=position;
    referenceTimesMs[i]=timeMs;
  }
  return new MlltSeeker(referencePositions,referenceTimesMs);
}
