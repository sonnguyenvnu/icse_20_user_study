/** 
 * <p> Return a simple string representation of this object. </p>
 */
@Override public String toString(){
  try {
    return getSummary();
  }
 catch (  SchedulerException se) {
    return "SchedulerMetaData: undeterminable.";
  }
}
