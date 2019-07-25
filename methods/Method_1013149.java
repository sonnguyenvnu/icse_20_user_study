public boolean has(final IFile iFile){
synchronized (items) {
    return items.stream().filter(i -> i.isInFile(iFile)).findAny().isPresent();
  }
}
