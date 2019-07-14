public Float getFileProgress(String location){
  if (location == null) {
    return null;
  }
  return fileProgresses.get(location);
}
