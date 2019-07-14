/** 
 * @return New instance of {@link EventTrigger} that is created by the current mComponentScope.
 */
<E>EventTrigger<E> newEventTrigger(String childKey,int id){
  String parentKey=mComponentScope == null ? "" : mComponentScope.getGlobalKey();
  return new EventTrigger<>(parentKey,id,childKey);
}
