void dispatchLastEvent(){
  if (mLastEventType == TYPE_NONE) {
    return;
  }
switch (mLastEventType) {
case Change.INSERT:
    List<RenderInfo> renderInfosInsert=collectComponentInfos(mLastEventPosition,mLastEventCount,mComponentInfoSparseArray);
  if (mLastEventCount > 1) {
    mTarget.insertRange(mLastEventPosition,mLastEventCount,renderInfosInsert);
    if (ENABLE_LOGGER) {
      logInsertIterative(mLastEventPosition,renderInfosInsert);
    }
  }
 else {
    mTarget.insert(mLastEventPosition,mComponentInfoSparseArray.get(mLastEventPosition));
    if (ENABLE_LOGGER) {
      mSectionsDebugLogger.logInsert(mSectionTreeTag,mLastEventPosition,mComponentInfoSparseArray.get(mLastEventPosition),Thread.currentThread().getName());
    }
  }
break;
case Change.DELETE:
if (mLastEventCount > 1) {
mTarget.deleteRange(mLastEventPosition,mLastEventCount);
if (ENABLE_LOGGER) {
  logDeleteIterative(mLastEventPosition,mLastEventCount);
}
}
 else {
mTarget.delete(mLastEventPosition);
if (ENABLE_LOGGER) {
  mSectionsDebugLogger.logDelete(mSectionTreeTag,mLastEventPosition,Thread.currentThread().getName());
}
}
break;
case Change.UPDATE:
List<RenderInfo> renderInfosUpdate=collectComponentInfos(mLastEventPosition,mLastEventCount,mComponentInfoSparseArray);
if (mLastEventCount > 1) {
mTarget.updateRange(mLastEventPosition,mLastEventCount,renderInfosUpdate);
if (ENABLE_LOGGER) {
logUpdateIterative(mLastEventPosition,renderInfosUpdate);
}
}
 else {
mTarget.update(mLastEventPosition,mComponentInfoSparseArray.get(mLastEventPosition));
if (ENABLE_LOGGER) {
mSectionsDebugLogger.logUpdate(mSectionTreeTag,mLastEventPosition,mComponentInfoSparseArray.get(mLastEventPosition),Thread.currentThread().getName());
}
}
break;
case Change.MOVE:
break;
}
mLastEventType=TYPE_NONE;
mComponentInfoSparseArray.clear();
}
