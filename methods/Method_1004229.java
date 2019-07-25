public SizedReader<T> reader(){
  return StatefulCopyable.copyIfNeeded(reader);
}
