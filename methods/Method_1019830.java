/** 
 * write new value (All operations need to meet the CAS mechanism)
 * @param update new value need to update
 */
public void update(long[] update){
  long[] current;
  long[] tmp=new long[update.length];
  do {
    current=values.get();
    for (int k=0; k < update.length && k < current.length; k++) {
      tmp[k]=current[k] + update[k];
    }
  }
 while (!values.compareAndSet(current,tmp));
}
