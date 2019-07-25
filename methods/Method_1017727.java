@Override public int attempts(){
  return successfulEvaluations + discards + (semiAttempts / 10);
}
