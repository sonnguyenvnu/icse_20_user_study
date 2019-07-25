public Iterator pipe(File directory){
  return new FileIterator(directory,fileFilter,labelPattern);
}
