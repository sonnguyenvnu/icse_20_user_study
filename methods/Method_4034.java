/** 
 * This method assumes that newItems are sorted and deduplicated.
 */
private void merge(T[] newData,int newDataSize){
  final boolean forceBatchedUpdates=!(mCallback instanceof BatchedCallback);
  if (forceBatchedUpdates) {
    beginBatchedUpdates();
  }
  mOldData=mData;
  mOldDataStart=0;
  mOldDataSize=mSize;
  final int mergedCapacity=mSize + newDataSize + CAPACITY_GROWTH;
  mData=(T[])Array.newInstance(mTClass,mergedCapacity);
  mNewDataStart=0;
  int newDataStart=0;
  while (mOldDataStart < mOldDataSize || newDataStart < newDataSize) {
    if (mOldDataStart == mOldDataSize) {
      int itemCount=newDataSize - newDataStart;
      System.arraycopy(newData,newDataStart,mData,mNewDataStart,itemCount);
      mNewDataStart+=itemCount;
      mSize+=itemCount;
      mCallback.onInserted(mNewDataStart - itemCount,itemCount);
      break;
    }
    if (newDataStart == newDataSize) {
      int itemCount=mOldDataSize - mOldDataStart;
      System.arraycopy(mOldData,mOldDataStart,mData,mNewDataStart,itemCount);
      mNewDataStart+=itemCount;
      break;
    }
    T oldItem=mOldData[mOldDataStart];
    T newItem=newData[newDataStart];
    int compare=mCallback.compare(oldItem,newItem);
    if (compare > 0) {
      mData[mNewDataStart++]=newItem;
      mSize++;
      newDataStart++;
      mCallback.onInserted(mNewDataStart - 1,1);
    }
 else     if (compare == 0 && mCallback.areItemsTheSame(oldItem,newItem)) {
      mData[mNewDataStart++]=newItem;
      newDataStart++;
      mOldDataStart++;
      if (!mCallback.areContentsTheSame(oldItem,newItem)) {
        mCallback.onChanged(mNewDataStart - 1,1,mCallback.getChangePayload(oldItem,newItem));
      }
    }
 else {
      mData[mNewDataStart++]=oldItem;
      mOldDataStart++;
    }
  }
  mOldData=null;
  if (forceBatchedUpdates) {
    endBatchedUpdates();
  }
}
