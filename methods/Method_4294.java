private static void overlapAdd(int frameCount,int channelCount,short[] out,int outPosition,short[] rampDown,int rampDownPosition,short[] rampUp,int rampUpPosition){
  for (int i=0; i < channelCount; i++) {
    int o=outPosition * channelCount + i;
    int u=rampUpPosition * channelCount + i;
    int d=rampDownPosition * channelCount + i;
    for (int t=0; t < frameCount; t++) {
      out[o]=(short)((rampDown[d] * (frameCount - t) + rampUp[u] * t) / frameCount);
      o+=channelCount;
      d+=channelCount;
      u+=channelCount;
    }
  }
}
