/** 
 * ????????,???NotSleep???????
 * @return
 */
@Override public Comparator<Long> getComparator(){
  return new Comparator<Long>(){
    @Override public int compare(    Long o1,    Long o2){
      return o1.compareTo(o2);
    }
  }
;
}
