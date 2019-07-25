/** 
 * @return modulars owned (declared) by this element.
 */
@NotNull @Override public StubBased[] modulars(){
  StubBased[] stubBaseds=PsiTreeUtil.getChildrenOfType(this,StubBased.class);
  List<StubBased> modularList=new ArrayList<StubBased>();
  if (stubBaseds != null) {
    for (    StubBased stubBased : stubBaseds) {
      if (Implementation.is(stubBased) || Module.Companion.is(stubBased) || Protocol.is(stubBased)) {
        modularList.add(stubBased);
      }
    }
  }
  return modularList.toArray(new StubBased[modularList.size()]);
}
