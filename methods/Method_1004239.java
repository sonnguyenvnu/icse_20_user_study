@Override public ListMarshaller<T> copy(){
  if (elementReader instanceof StatefulCopyable || elementWriter instanceof StatefulCopyable) {
    return new ListMarshaller<>(copyIfNeeded(elementReader),copyIfNeeded(elementWriter));
  }
 else {
    return this;
  }
}
