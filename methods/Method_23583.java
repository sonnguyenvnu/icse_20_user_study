public double pop(){
  if (count == 0) {
    throw new RuntimeException("Can't call pop() on an empty list");
  }
  double value=get(count - 1);
  count--;
  return value;
}
