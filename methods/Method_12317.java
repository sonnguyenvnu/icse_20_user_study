private void postInit(GifViewUtils.GifImageViewAttributes result){
  mFreezesAnimation=result.freezesAnimation;
  if (result.mSourceResId > 0) {
    super.setImageResource(result.mSourceResId);
  }
  if (result.mBackgroundResId > 0) {
    super.setBackgroundResource(result.mBackgroundResId);
  }
}
