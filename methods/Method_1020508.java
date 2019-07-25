private Optional<SourcePosition> emit(Runnable codeEmitter){
  FilePosition startPosition=getCurrentPosition();
  codeEmitter.run();
  FilePosition endPosition=getCurrentPosition();
  if (endPosition.equals(startPosition)) {
    return Optional.empty();
  }
  return Optional.of(SourcePosition.newBuilder().setStartFilePosition(startPosition).setEndFilePosition(endPosition).build());
}
