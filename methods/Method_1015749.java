/** 
 * Removes the next non-null element and nulls the index if nullify=true 
 */
public T remove(boolean nullify){
  lock.lock();
  try {
    int row_index=computeRow(hd + 1);
    if (row_index < 0 || row_index >= matrix.length)     return null;
    T[] row=matrix[row_index];
    if (row == null)     return null;
    int index=computeIndex(hd + 1);
    if (index < 0)     return null;
    T existing_element=row[index];
    if (existing_element != null) {
      hd++;
      size=Math.max(size - 1,0);
      if (nullify) {
        row[index]=null;
        if (hd - low > 0)         low=hd;
      }
    }
    return existing_element;
  }
  finally {
    lock.unlock();
  }
}
