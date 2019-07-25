public void deleted(IFile file){
  MapSequence.fromMap(files).put(file,FilesDelta.Status.DELETED);
}
