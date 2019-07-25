public void written(IFile file){
  MapSequence.fromMap(files).put(file,FilesDelta.Status.WRITTEN);
}
