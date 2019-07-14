public void linkAndCopyToken(CommonHiddenStreamToken prev,CommonHiddenStreamToken monitored){
  hiddenCopy=partialCloneToken(LA(1));
  prev.setHiddenAfter(hiddenCopy);
  if (prev != monitored) {
    hiddenCopy.setHiddenBefore(prev);
  }
  return;
}
