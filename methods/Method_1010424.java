public void stale(IFile file){
  if (!(MapSequence.fromMap(files).containsKey(file))) {
    MapSequence.fromMap(files).put(file,FilesDelta.Status.STALE);
  }
}
