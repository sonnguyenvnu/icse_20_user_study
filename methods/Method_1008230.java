@Override public Diff<T> diff(T previousState){
  if (this.get().equals(previousState)) {
    return new CompleteNamedDiff<>(previousState.getWriteableName(),previousState.getMinimalSupportedVersion());
  }
 else {
    return new CompleteNamedDiff<>(get());
  }
}
