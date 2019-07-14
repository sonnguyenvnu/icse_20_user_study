static protected void sortRecordElementCandidates(List<RecordElementCandidate> list){
  Collections.sort(list,new Comparator<RecordElementCandidate>(){
    @Override public int compare(    RecordElementCandidate o1,    RecordElementCandidate o2){
      return o2.count - o1.count;
    }
  }
);
}
