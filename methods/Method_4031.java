private void replaceAllRemove(){
  mSize--;
  mOldDataStart++;
  mCallback.onRemoved(mNewDataStart,1);
}
