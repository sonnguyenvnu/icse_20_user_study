@SuppressLint("RestrictedApi") private void init(AttributeSet attrs,int defStyleAttr,int defStyleRes){
  TintTypedArray a=TintTypedArray.obtainStyledAttributes(getContext(),attrs,R.styleable.ContentStateLayout,defStyleAttr,defStyleRes);
  mLoadingViewId=a.getResourceId(R.styleable.ContentStateLayout_loadingView,R.id.loading);
  mContentViewId=a.getResourceId(R.styleable.ContentStateLayout_contentView,R.id.content);
  mEmptyViewId=a.getResourceId(R.styleable.ContentStateLayout_emptyView,R.id.empty);
  mErrorViewId=a.getResourceId(R.styleable.ContentStateLayout_errorView,R.id.error);
  mAnimationEnabled=a.getBoolean(R.styleable.ContentStateLayout_animationEnabled,true);
  a.recycle();
}
