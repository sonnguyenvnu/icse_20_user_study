private Object readResolve(){
  return new Result(serializedForm);
}
