@Override public void update(ProgressMonitor monitor,@NotNull FileSystemEvent event){
  for (  IFile file : event.getChanged()) {
    if (file.equals(myFile)) {
      fireChanged(monitor);
      break;
    }
  }
}
