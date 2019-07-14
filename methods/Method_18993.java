/** 
 * @return a new Change with an index equal to its current index plus an offset. This is usedinternally by the framework when generating the final  {@link ChangeSet} for the{@link com.facebook.litho.sections.SectionTree.Target}.
 */
static Change offset(Change change,int offset){
  final int toIndex=change.mToIndex >= 0 ? change.mToIndex + offset : -1;
  return acquire(change.mType,change.mIndex + offset,toIndex,change.mCount,change.mRenderInfo,change.mRenderInfos,change.mPrevData,change.mNextData);
}
