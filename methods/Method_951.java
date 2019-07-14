@Nullable @Override public DrawableFactory getAnimatedDrawableFactory(Context context){
  if (mAnimatedDrawableFactory == null) {
    mAnimatedDrawableFactory=createDrawableFactory();
  }
  return mAnimatedDrawableFactory;
}
