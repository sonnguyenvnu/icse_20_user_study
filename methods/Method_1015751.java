protected boolean _add(long seqno,T element,boolean check_if_resize_needed,Predicate<T> remove_filter){
  if (seqno - hd <= 0)   return false;
  int row_index=computeRow(seqno);
  if (check_if_resize_needed && row_index >= matrix.length) {
    resize(seqno);
    row_index=computeRow(seqno);
  }
  T[] row=getRow(row_index);
  int index=computeIndex(seqno);
  T existing_element=row[index];
  if (existing_element == null) {
    row[index]=element;
    size++;
    if (seqno - hr > 0)     hr=seqno;
    if (remove_filter != null && hd + 1 == seqno) {
      forEach(hd + 1,hr,(seq,msg,r,c) -> {
        if (msg == null || !remove_filter.test(msg))         return false;
        if (seq - hd > 0)         hd=seq;
        size=Math.max(size - 1,0);
        return true;
      }
);
    }
    return true;
  }
  return false;
}
