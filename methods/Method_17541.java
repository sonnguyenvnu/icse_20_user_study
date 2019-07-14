public double admissionRate(){
  long candidateCount=admittedCount + rejectedCount;
  return (candidateCount == 0) ? 1.0 : (double)admittedCount / candidateCount;
}
