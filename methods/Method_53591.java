protected void deleteTag(TagView tagView){
  removeView(tagView);
  if (mOnTagChangeListener != null) {
    mOnTagChangeListener.onDelete(TagGroup.this,tagView.getText().toString());
  }
}
