@Override @SuppressWarnings("unchecked") public <T>Resume<T,T> passthrough(){
  return (Resume<T,T>)PASSTHROUGH;
}
