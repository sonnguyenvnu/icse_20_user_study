public String pop(){
  if (count == 0) {
    throw new RuntimeException("Can't call pop() on an empty list");
  }
  String value=get(count - 1);
  data[--count]=null;
  return value;
}
