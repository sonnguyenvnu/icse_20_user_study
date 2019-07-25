@Override public void verify(Iterable<?> value,Annotation annotation){
  checkArgument(!Iterables.isEmpty(value),"Value must not be empty.");
}
