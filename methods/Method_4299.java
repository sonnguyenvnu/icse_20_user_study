@Override public boolean isActive(){
  return sampleRateHz != Format.NO_VALUE && (Math.abs(speed - 1f) >= CLOSE_THRESHOLD || Math.abs(pitch - 1f) >= CLOSE_THRESHOLD || outputSampleRateHz != sampleRateHz);
}
