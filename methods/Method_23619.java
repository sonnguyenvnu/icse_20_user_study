public float sum(){
  double amount=sumDouble();
  if (amount > Float.MAX_VALUE) {
    throw new RuntimeException("sum() exceeds " + Float.MAX_VALUE + ", use sumDouble()");
  }
  if (amount < -Float.MAX_VALUE) {
    throw new RuntimeException("sum() lower than " + -Float.MAX_VALUE + ", use sumDouble()");
  }
  return (float)amount;
}
