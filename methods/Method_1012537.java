String dump(){
  if (waveform == WF_PULSE)   flags|=FLAG_PULSE_DUTY;
 else   flags&=~FLAG_PULSE_DUTY;
  return super.dump() + " " + waveform + " " + frequency + " " + maxVoltage + " " + bias + " " + phaseShift + " " + dutyCycle;
}
