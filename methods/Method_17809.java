public Component makeShallowCopy(){
  try {
    final Component component=(Component)super.clone();
    component.mGlobalKey=null;
    component.mIsLayoutStarted=false;
    component.mHasManualKey=false;
    component.mLayoutVersionGenerator=new AtomicBoolean();
    component.mScopedContext=null;
    component.mChildCounters=null;
    component.mManualKeys=null;
    return component;
  }
 catch (  CloneNotSupportedException e) {
    throw new RuntimeException(e);
  }
}
