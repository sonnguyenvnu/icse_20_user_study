public float[] getCurrentRange(){
  float range=maxValue - minValue;
  if (mSeekBarMode == 2) {
    return new float[]{-offsetValue + minValue + range * leftSB.currPercent,-offsetValue + minValue + range * rightSB.currPercent};
  }
 else {
    return new float[]{-offsetValue + minValue + range * leftSB.currPercent,-offsetValue + minValue + range * 1.0f};
  }
}
