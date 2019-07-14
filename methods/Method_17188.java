@SuppressWarnings("unchecked") public static <E>E[] allocate(int capacity){
  return (E[])new Object[capacity];
}
