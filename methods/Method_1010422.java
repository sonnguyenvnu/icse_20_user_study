public void kept(IFile file){
  MapSequence.fromMap(files).put(file,FilesDelta.Status.KEPT);
}
