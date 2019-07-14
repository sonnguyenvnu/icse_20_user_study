/** 
 * ??????
 * @param procDefId ????ID
 * @return ActivityImpl       ???????
 */
@Override public ActivityImpl getActivityById(String procDefId,String activityId){
  return getProcessDefinition(procDefId).findActivity(activityId);
}
