@Override public SetMarshaller<T> copy(){
  return new SetMarshaller<>(copyIfNeeded(elementReader),copyIfNeeded(elementWriter));
}
