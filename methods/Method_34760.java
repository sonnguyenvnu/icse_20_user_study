public static HystrixCollapserEvent from(HystrixCollapserKey collapserKey,HystrixEventType.Collapser eventType,int count){
  return new HystrixCollapserEvent(collapserKey,eventType,count);
}
