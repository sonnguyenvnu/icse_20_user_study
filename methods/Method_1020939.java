/** 
 * ?????????qps
 */
public boolean check(String type){
  Integer max=qpsConfs.get(type);
  if (max == null) {
    return false;
  }
  QpsValue currentQpsValue=currentQps.get(type);
  if (currentQpsValue == null) {
synchronized (currentQps) {
      currentQpsValue=currentQps.get(type);
      if (currentQpsValue == null) {
        currentQpsValue=new QpsValue();
        currentQps.put(type,currentQpsValue);
      }
    }
  }
  currentQpsValue.value.incrementAndGet();
  if (max != null && currentQpsValue != null && max.intValue() < (currentQpsValue.value.get() * 1000 / timePeriod)) {
    return true;
  }
 else {
    return false;
  }
}
