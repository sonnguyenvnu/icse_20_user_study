public static boolean contains(ActionGroup container,ActionGroup what){
  if (container == what)   return true;
  if (container instanceof RecentProjectsGroup)   return false;
  for (  AnAction child : container.getChildren(null)) {
    if (child instanceof ActionGroup) {
      if (contains((ActionGroup)child,what))       return true;
    }
  }
  return false;
}
