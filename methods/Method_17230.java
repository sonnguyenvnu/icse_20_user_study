/** 
 * CASes the tableBusy field from 0 to 1 to acquire lock. 
 */
final boolean casTableBusy(){
  return UnsafeAccess.UNSAFE.compareAndSwapInt(this,TABLE_BUSY,0,1);
}
