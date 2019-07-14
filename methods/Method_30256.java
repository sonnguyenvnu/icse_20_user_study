@Override public void onActivityCreated(Bundle savedInstanceState){
  super.onActivityCreated(savedInstanceState);
  mResource=onAttachResource(mItemId,mSimpleItem,mItem);
  AppCompatActivity activity=(AppCompatActivity)getActivity();
  activity.setSupportActionBar(mToolbar);
  float backdropRatio=getBackdropRatio();
  boolean hasBackdrop=backdropRatio > 0;
  if (hasBackdrop) {
    StatusBarColorUtils.set(Color.TRANSPARENT,activity);
    ViewUtils.setLayoutFullscreen(activity);
  }
  mBackdropImage.setRatio(backdropRatio);
  ViewCompat.setBackground(mBackdropScrim,DrawableUtils.makeScrimDrawable(Gravity.TOP));
  mContentList.setLayoutManager(new LinearLayoutManager(activity));
  mAdapter=onCreateAdapter();
  mContentList.setAdapter(mAdapter);
  mContentList.setBackdropRatio(backdropRatio);
  mContentList.setPaddingTopPaddingExtra(getContentListPaddingTopExtra());
  if (hasBackdrop) {
    mContentList.setBackdropWrapper(mBackdropWrapperLayout);
    mContentList.addOnScrollListener(new RecyclerView.OnScrollListener(){
      @Override public void onScrolled(      RecyclerView recyclerView,      int dx,      int dy){
        if (recyclerView.getChildCount() == 0) {
          return;
        }
        View firstChild=recyclerView.getChildAt(0);
        int firstPosition=recyclerView.getChildAdapterPosition(firstChild);
        boolean firstItemInLayout=firstPosition == 0;
        if (mScrollY == 0) {
          if (!firstItemInLayout) {
            ViewUtils.setVisibleOrInvisible(mBackdropLayout,false);
            return;
          }
 else {
            ViewUtils.fadeIn(mBackdropLayout);
            mScrollY=recyclerView.getPaddingTop() - firstChild.getTop();
          }
        }
 else {
          mScrollY+=dy;
        }
        mBackdropLayout.setTranslationY((float)-mScrollY / 2);
        mBackdropScrim.setTranslationY((float)mScrollY / 2);
      }
    }
);
    int colorPrimaryDark=ViewUtils.getColorFromAttrRes(R.attr.colorPrimaryDark,0,activity);
    mContentList.addOnScrollListener(new OnVerticalScrollWithPagingTouchSlopListener(activity){
      @Override public void onScrolledUp(){
        if (mAppBarWrapperLayout.isHidden()) {
          mToolbar.setTransparent(!hasFirstChildReachedTop());
        }
        mAppBarWrapperLayout.show();
      }
      @Override public void onScrolledDown(){
        if (hasFirstChildReachedTop()) {
          mAppBarWrapperLayout.hide();
        }
      }
      @Override public void onScrolled(      int dy){
        boolean initialize=dy == 0;
        boolean hasFirstChildReachedTop=hasFirstChildReachedTop();
        int statusBarColor=hasFirstChildReachedTop ? colorPrimaryDark : Color.TRANSPARENT;
        if (mStatusBarColor != statusBarColor) {
          mStatusBarColor=statusBarColor;
          if (initialize) {
            StatusBarColorUtils.set(mStatusBarColor,activity);
          }
 else {
            StatusBarColorUtils.animateTo(mStatusBarColor,activity);
          }
        }
        if (mAppBarWrapperLayout.isShowing()) {
          if (initialize) {
            mToolbar.setTransparent(!hasFirstChildReachedTop);
          }
 else {
            mToolbar.animateToTransparent(!hasFirstChildReachedTop);
          }
        }
      }
      private boolean hasFirstChildReachedTop(){
        return RecyclerViewUtils.hasFirstChildReachedTop(mContentList,mToolbar.getBottom());
      }
    }
);
  }
 else {
    ViewUtils.setVisibleOrGone(mBackdropWrapperLayout,false);
    mContentList.addOnScrollListener(new OnVerticalScrollWithPagingTouchSlopListener(activity){
      @Override public void onScrolledUp(){
        mAppBarWrapperLayout.show();
      }
      @Override public void onScrolledDown(){
        if (hasFirstChildReachedTop()) {
          mAppBarWrapperLayout.hide();
        }
      }
      private boolean hasFirstChildReachedTop(){
        return RecyclerViewUtils.hasFirstChildReachedTop(mContentList,0);
      }
    }
);
  }
  mToolbar.setOnDoubleClickListener(view -> {
    mContentList.smoothScrollToPosition(0);
    return true;
  }
);
  mContentStateViewsLayout.setBackdropRatio(backdropRatio);
  mContentStateViewsLayout.setPaddingTopPaddingExtra(getContentStateViewsPaddingTopExtra());
  if (mResource.hasSimpleItem()) {
    updateWithSimpleItem(mResource.getSimpleItem());
  }
  mContentStateLayout.setLoading();
  if (mResource.isAnyLoaded()) {
    mResource.notifyChanged();
  }
  if (hasBackdrop && mAdapter.getItemCount() == 0) {
    mToolbar.getBackground().setAlpha(0);
  }
}
