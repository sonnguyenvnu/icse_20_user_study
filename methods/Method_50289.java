private void setSizeProvider(Builder builder){
  mSizeProvider=builder.mSizeProvider;
  if (mSizeProvider == null) {
    mSizeProvider=(position,parent) -> DEFAULT_SIZE;
  }
}
