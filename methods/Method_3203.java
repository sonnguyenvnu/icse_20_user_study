private List<Map.Entry<String,Double>> topN(Map<String,Double> tfidfs,int size){
  MaxHeap<Map.Entry<String,Double>> heap=new MaxHeap<Map.Entry<String,Double>>(size,new Comparator<Map.Entry<String,Double>>(){
    @Override public int compare(    Map.Entry<String,Double> o1,    Map.Entry<String,Double> o2){
      return o1.getValue().compareTo(o2.getValue());
    }
  }
);
  heap.addAll(tfidfs.entrySet());
  return heap.toList();
}
