private void toggleItemView(FileWrapper fileWrapper,int adapterPosition,boolean selected){
  File file=fileWrapper.file;
  fileWrapper.selected=selected;
  if (selected) {
    mSelectedFiles.add(file);
  }
 else {
    if (mSelectedFiles.indexOf(file) != -1) {
      mSelectedFiles.remove(file);
    }
  }
  mAdapter.notifyItemChanged(adapterPosition);
  mActionModeCallback.updateSelectedItemCount(mSelectedFiles.size());
}
