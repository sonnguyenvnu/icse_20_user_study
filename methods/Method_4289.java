private short interpolate(short[] in,int inPos,int oldSampleRate,int newSampleRate){
  short left=in[inPos];
  short right=in[inPos + channelCount];
  int position=newRatePosition * oldSampleRate;
  int leftPosition=oldRatePosition * newSampleRate;
  int rightPosition=(oldRatePosition + 1) * newSampleRate;
  int ratio=rightPosition - position;
  int width=rightPosition - leftPosition;
  return (short)((ratio * left + (width - ratio) * right) / width);
}
