private void downSampleInput(short[] samples,int position,int skip){
  int frameCount=maxRequiredFrameCount / skip;
  int samplesPerValue=channelCount * skip;
  position*=channelCount;
  for (int i=0; i < frameCount; i++) {
    int value=0;
    for (int j=0; j < samplesPerValue; j++) {
      value+=samples[position + i * samplesPerValue + j];
    }
    value/=samplesPerValue;
    downSampleBuffer[i]=(short)value;
  }
}
