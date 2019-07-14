public void shutdown(){
  writeOnlyCommandStartSubject.onCompleted();
  writeOnlyCommandCompletionSubject.onCompleted();
  writeOnlyCollapserSubject.onCompleted();
}
