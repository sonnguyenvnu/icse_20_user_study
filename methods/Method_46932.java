protected void addDatapoint(DatapointParcelable datapoint){
  if (getDataPackages().isEmpty()) {
    throw new IllegalStateException("This is the first datapoint!");
  }
  putDataPackage(datapoint);
  if (getProgressListener() != null) {
    getProgressListener().onUpdate(datapoint);
    if (datapoint.completed)     getProgressListener().refresh();
  }
}
