@SuppressWarnings("unchecked") public static <T>T choose(Collection<T> items,SourceOfRandomness random){
  Object[] asArray=items.toArray(new Object[items.size()]);
  return (T)asArray[random.nextInt(items.size())];
}
