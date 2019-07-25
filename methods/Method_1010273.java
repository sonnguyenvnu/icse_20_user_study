@Override public void update(ProgressMonitor monitor,@NotNull FileSystemEvent event){
  Set<String> affectedStreams=new HashSet<>();
  for (  IFile file : event.getChanged()) {
    if (isIncluded(file)) {
      affectedStreams.add(getStreamName(file));
      break;
    }
  }
  for (  IFile file : event.getCreated()) {
    if (isIncluded(file)) {
      affectedStreams.add(getStreamName(file));
      myLastAddRemove=new Date().getTime();
      break;
    }
  }
  for (  IFile file : event.getRemoved()) {
    if (isIncluded(file)) {
      affectedStreams.add(getStreamName(file));
      myLastAddRemove=new Date().getTime();
      break;
    }
  }
  fireChanged(monitor,affectedStreams);
}
