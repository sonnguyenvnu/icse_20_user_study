/** 
 * @return New instance of {@link EventTrigger} that is created by the current mScope. 
 */
<E>EventTrigger<E> newEventTrigger(String childKey,int id){
  final Section section=mScope == null ? null : mScope.get();
  String parentKey=section == null ? "" : section.getGlobalKey();
  return new EventTrigger<>(parentKey,id,childKey);
}
