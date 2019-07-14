private String generateUniqueGlobalKeyForChild(Section section,String childKey){
  final KeyHandler keyHandler=mScopedContext.getKeyHandler();
  if (!keyHandler.hasKey(childKey)) {
    return childKey;
  }
  final String childType=section.getSimpleName();
  if (mChildCounters == null) {
    mChildCounters=new HashMap<>();
  }
  final int childIndex=mChildCounters.containsKey(childType) ? mChildCounters.get(childType) : 0;
  mChildCounters.put(childType,childIndex + 1);
  return childKey + childIndex;
}
