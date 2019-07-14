public void setFrequency(HashMap<Timestamp,Integer[]> frequency){
  this.frequency=frequency;
  maxFreq=getMaxFreq(frequency);
  postInvalidate();
}
