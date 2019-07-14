public float pop(){
  if (count == 0) {
    throw new RuntimeException("Can't call pop() on an empty list");
  }
  float value=get(count - 1);
  count--;
  return value;
}
