@Override public Instance pipe(Instance carrier){
  File directory=(File)carrier.getData();
  carrier.setData(new FileIterator(directory,fileFilter,labelPattern));
  return carrier;
}
