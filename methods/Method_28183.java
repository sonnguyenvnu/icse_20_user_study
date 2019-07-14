@Override public long getCommentId(){
  return getArguments() != null ? getArguments().getLong(BundleConstant.ID) : 0;
}
