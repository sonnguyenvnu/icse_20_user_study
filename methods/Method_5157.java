private SeekParameters clipSeekParameters(long positionUs,SeekParameters seekParameters){
  long toleranceBeforeUs=Util.constrainValue(seekParameters.toleranceBeforeUs,0,positionUs - startUs);
  long toleranceAfterUs=Util.constrainValue(seekParameters.toleranceAfterUs,0,endUs == C.TIME_END_OF_SOURCE ? Long.MAX_VALUE : endUs - positionUs);
  if (toleranceBeforeUs == seekParameters.toleranceBeforeUs && toleranceAfterUs == seekParameters.toleranceAfterUs) {
    return seekParameters;
  }
 else {
    return new SeekParameters(toleranceBeforeUs,toleranceAfterUs);
  }
}
