/** 
 * Removes all elements less than or equal to seqno from the table. Does this by nulling entire rows in the matrix and nulling all elements < index(seqno) of the first row that cannot be removed.
 * @param seqno All elements <= seqno will be nulled
 * @param force If true, we only ensure that seqno <= hr, but don't care about hd, and set hd=low=seqno.
 */
public void purge(long seqno,boolean force){
  lock.lock();
  try {
    if (seqno - low <= 0)     return;
    if (force) {
      if (seqno - hr > 0)       seqno=hr;
    }
 else {
      if (seqno - hd > 0)       seqno=hd;
    }
    int start_row=computeRow(low), end_row=computeRow(seqno);
    if (start_row < 0)     start_row=0;
    if (end_row < 0)     return;
    for (int i=start_row; i < end_row; i++)     matrix[i]=null;
    if (matrix[end_row] != null) {
      int index=computeIndex(seqno);
      for (int i=0; i <= index; i++)       matrix[end_row][i]=null;
    }
    if (seqno - low > 0)     low=seqno;
    if (force) {
      if (seqno - hd > 0)       low=hd=seqno;
      size=computeSize();
    }
    num_purges++;
    if (max_compaction_time <= 0)     return;
    long current_time=System.nanoTime();
    if (last_compaction_timestamp > 0) {
      if (current_time - last_compaction_timestamp >= max_compaction_time) {
        _compact();
        last_compaction_timestamp=current_time;
      }
    }
 else     last_compaction_timestamp=current_time;
  }
  finally {
    lock.unlock();
  }
}
