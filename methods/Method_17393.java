@Override public boolean admit(long candidateKey,long victimKey){
  sketch.reportMiss();
  long candidateFreq=sketch.frequency(candidateKey);
  long victimFreq=sketch.frequency(victimKey);
  if (candidateFreq > victimFreq) {
    policyStats.recordAdmission();
    return true;
  }
  policyStats.recordRejection();
  return false;
}
