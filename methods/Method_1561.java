@Nullable public synchronized T removeFromEnd(){
  LinkedEntry<T> last=mTail;
  if (last == null) {
    return null;
  }
  T value=last.value.pollLast();
  maybePrune(last);
  return value;
}
