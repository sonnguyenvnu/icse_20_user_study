String dump(){
  flags|=FLAG_GAIN;
  return super.dump() + " " + maxOut + " " + minOut + " " + gbw + " " + volts[0] + " " + volts[1] + " " + gain;
}
