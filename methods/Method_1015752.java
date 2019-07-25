/** 
 * Moves rows down the matrix, by removing purged rows. If resizing to accommodate seqno is still needed, computes a new size. Then either moves existing rows down, or copies them into a new array (if resizing took place). The lock must be held by the caller of resize(). 
 */
@GuardedBy("lock") protected void resize(long seqno){
  int num_rows_to_purge=computeRow(low);
  int row_index=computeRow(seqno) - num_rows_to_purge;
  if (row_index < 0)   return;
  int new_size=Math.max(row_index + 1,matrix.length);
  if (new_size > matrix.length) {
    T[][] new_matrix=(T[][])new Object[new_size][];
    System.arraycopy(matrix,num_rows_to_purge,new_matrix,0,matrix.length - num_rows_to_purge);
    matrix=new_matrix;
    num_resizes++;
  }
 else   if (num_rows_to_purge > 0) {
    move(num_rows_to_purge);
  }
  offset+=(num_rows_to_purge * elements_per_row);
}
