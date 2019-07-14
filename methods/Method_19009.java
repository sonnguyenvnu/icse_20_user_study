/** 
 * @return an empty ChangeSet starting from count startCount. 
 */
static ChangeSet acquireChangeSet(int startCount,Section section,boolean enableStats){
  final ChangeSet changeSet=acquire();
  changeSet.mFinalCount=startCount;
  changeSet.mSection=section;
  changeSet.mChangeSetStats=enableStats ? new ChangeSetStats() : null;
  return changeSet;
}
