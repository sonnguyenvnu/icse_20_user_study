public static boolean compare(Collection collection,Object target){
  if (collection == target) {
    return true;
  }
  if (collection == null || target == null) {
    return false;
  }
  Collection targetCollection=null;
  if (target instanceof String) {
    target=((String)target).split("[, ;]");
  }
  if (target instanceof Collection) {
    targetCollection=((Collection)target);
  }
 else   if (target.getClass().isArray()) {
    targetCollection=Arrays.asList(((Object[])target));
  }
  if (targetCollection == null) {
    return false;
  }
  Set left=new HashSet(collection);
  Set right=new HashSet(targetCollection);
  if (left.size() < right.size()) {
    Set tmp=right;
    right=left;
    left=tmp;
  }
  l:   for (  Object source : left) {
    if (!right.stream().anyMatch(targetObj -> compare(source,targetObj))) {
      return false;
    }
  }
  return true;
}
