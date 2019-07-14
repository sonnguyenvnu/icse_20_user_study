/** 
 * getBoundaryEventOutgoingTransitions
 * @param activity
 * @return
 */
private List<PvmTransition> getBoundaryEventOutgoingTransitions(ActivityImpl activity){
  List<PvmTransition> boundaryTrans=new ArrayList<PvmTransition>();
  for (  ActivityImpl subActivity : activity.getActivities()) {
    String type=(String)subActivity.getProperty("type");
    if (type != null && type.toLowerCase().indexOf("boundary") >= 0) {
      boundaryTrans.addAll(subActivity.getOutgoingTransitions());
    }
  }
  return boundaryTrans;
}
