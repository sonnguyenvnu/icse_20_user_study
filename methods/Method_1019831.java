/** 
 * Empty the slot after print The value may have been updated during printing So pass in the array of values ??that need to be cleared, minus the value that has been printed. (All operations need to meet the CAS mechanism)
 * @param toBeClear toBeClear
 */
public void clear(long[] toBeClear){
  long[] current;
  long[] tmp=new long[toBeClear.length];
  do {
    current=values.get();
    for (int k=0; k < current.length && k < toBeClear.length; k++) {
      tmp[k]=current[k] - toBeClear[k];
    }
  }
 while (!values.compareAndSet(current,tmp));
}
