private void sortImpl(final boolean reverse){
  new Sort(){
    @Override public int size(){
      return count;
    }
    @Override public int compare(    int a,    int b){
      int diff=data[a].compareToIgnoreCase(data[b]);
      return reverse ? -diff : diff;
    }
    @Override public void swap(    int a,    int b){
      String temp=data[a];
      data[a]=data[b];
      data[b]=temp;
    }
  }
.run();
}
