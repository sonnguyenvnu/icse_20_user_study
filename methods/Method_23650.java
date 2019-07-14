public int sum(){
  long amount=sumLong();
  if (amount > Integer.MAX_VALUE) {
    throw new RuntimeException("sum() exceeds " + Integer.MAX_VALUE + ", use sumLong()");
  }
  if (amount < Integer.MIN_VALUE) {
    throw new RuntimeException("sum() less than " + Integer.MIN_VALUE + ", use sumLong()");
  }
  return (int)amount;
}
