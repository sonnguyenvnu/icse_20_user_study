/** 
 * @return a new Change that is a copy of a given Change.
 */
static Change copy(Change change){
  return acquire(change.mType,change.mIndex,change.mToIndex,change.mCount,change.mRenderInfo,change.mRenderInfos,change.mPrevData,change.mNextData);
}
