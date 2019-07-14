private void applyChangeSetsToTargetUnchecked(final Section currentSection,@Nullable final Section oldSection,List<ChangeSet> changeSets,final int source,@Nullable final String attribution){
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("applyChangeSetToTarget");
  }
  boolean appliedChanges=false;
  ChangeSet mergedChangeSet=null;
  try {
    for (int i=0, size=changeSets.size(); i < size; i++) {
      final ChangeSet changeSet=changeSets.get(i);
      if (changeSet.getChangeCount() > 0) {
        for (int j=0, changeSize=changeSet.getChangeCount(); j < changeSize; j++) {
          final Change change=changeSet.getChangeAt(j);
switch (change.getType()) {
case Change.INSERT:
            appliedChanges=true;
          mTarget.insert(change.getIndex(),change.getRenderInfo());
        break;
case Change.INSERT_RANGE:
      appliedChanges=true;
    mTarget.insertRange(change.getIndex(),change.getCount(),change.getRenderInfos());
  break;
case Change.UPDATE:
appliedChanges=true;
mTarget.update(change.getIndex(),change.getRenderInfo());
break;
case Change.UPDATE_RANGE:
appliedChanges=true;
mTarget.updateRange(change.getIndex(),change.getCount(),change.getRenderInfos());
break;
case Change.DELETE:
appliedChanges=true;
mTarget.delete(change.getIndex());
break;
case Change.DELETE_RANGE:
appliedChanges=true;
mTarget.deleteRange(change.getIndex(),change.getCount());
break;
case Change.MOVE:
appliedChanges=true;
mTarget.move(change.getIndex(),change.getToIndex());
}
}
mTarget.dispatchLastEvent();
}
mergedChangeSet=ChangeSet.merge(mergedChangeSet,changeSet);
}
final boolean isDataChanged=appliedChanges;
final ChangesInfo changesInfo=new ChangesInfo(mergedChangeSet != null ? mergedChangeSet.getChanges() : Collections.<Change>emptyList());
mTarget.notifyChangeSetComplete(isDataChanged,new ChangeSetCompleteCallback(){
@Override public void onDataBound(){
if (mChangesetDebug != null) {
mChangesetDebug.onChangesetApplied(mCurrentSection,oldSection,changesInfo,mContext.getLogTag() == null ? "SectionTree" + SectionTree.this.mTag : mContext.getLogTag(),source,attribution);
}
if (!isDataChanged) {
return;
}
if (isTracing) {
ComponentsSystrace.beginSection("dataBound");
}
try {
dataBound(currentSection);
}
  finally {
if (isTracing) {
ComponentsSystrace.endSection();
}
}
}
@Override public void onDataRendered(boolean isMounted,long uptimeMillis){
dataRendered(currentSection,isDataChanged,isMounted,uptimeMillis,changesInfo);
}
}
);
}
  finally {
if (isTracing) {
ComponentsSystrace.endSection();
}
}
}
