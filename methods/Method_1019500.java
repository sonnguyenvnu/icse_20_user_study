private void push(final T item,final double wt,final boolean mark){
  data_.set(h_,item);
  weights_.set(h_,wt);
  if (marks_ != null) {
    marks_.set(h_,mark);
    numMarksInH_+=(mark ? 1 : 0);
  }
  ++h_;
  restoreTowardsRoot(h_ - 1);
}
