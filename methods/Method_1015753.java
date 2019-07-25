/** 
 * Moves contents of matrix num_rows down. Avoids a System.arraycopy(). Caller must hold the lock. 
 */
@GuardedBy("lock") protected void move(int num_rows){
  if (num_rows <= 0 || num_rows > matrix.length)   return;
  int target_index=0;
  for (int i=num_rows; i < matrix.length; i++)   matrix[target_index++]=matrix[i];
  for (int i=matrix.length - num_rows; i < matrix.length; i++)   matrix[i]=null;
  num_moves++;
}
