/** 
 * Unlock just this table.
 * @param t the table to unlock
 */
void unlock(Table t){
  locks.remove(t);
}
