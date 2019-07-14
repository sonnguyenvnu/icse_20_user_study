private void updateInterfaceForCurrentPage(int order){
  if (currentPage == null || currentPage.cached_page == null) {
    return;
  }
  isRtl=currentPage.cached_page.rtl;
  channelBlock=null;
  titleTextView.setText(currentPage.site_name == null ? "" : currentPage.site_name);
  if (order != 0) {
    WebpageAdapter adapterToUpdate=adapter[1];
    adapter[1]=adapter[0];
    adapter[0]=adapterToUpdate;
    RecyclerListView listToUpdate=listView[1];
    listView[1]=listView[0];
    listView[0]=listToUpdate;
    LinearLayoutManager layoutManagerToUpdate=layoutManager[1];
    layoutManager[1]=layoutManager[0];
    layoutManager[0]=layoutManagerToUpdate;
    int index1=containerView.indexOfChild(listView[0]);
    int index2=containerView.indexOfChild(listView[1]);
    if (order == 1) {
      if (index1 < index2) {
        containerView.removeView(listView[0]);
        containerView.addView(listView[0],index2);
      }
    }
 else {
      if (index2 < index1) {
        containerView.removeView(listView[0]);
        containerView.addView(listView[0],index1);
      }
    }
    pageSwitchAnimation=new AnimatorSet();
    listView[0].setVisibility(View.VISIBLE);
    int index=order == 1 ? 0 : 1;
    listView[index].setBackgroundColor(backgroundPaint.getColor());
    if (Build.VERSION.SDK_INT >= 18) {
      listView[index].setLayerType(View.LAYER_TYPE_HARDWARE,null);
    }
    if (order == 1) {
      pageSwitchAnimation.playTogether(ObjectAnimator.ofFloat(listView[0],View.TRANSLATION_X,AndroidUtilities.dp(56),0),ObjectAnimator.ofFloat(listView[0],View.ALPHA,0.0f,1.0f));
    }
 else     if (order == -1) {
      listView[0].setAlpha(1.0f);
      listView[0].setTranslationX(0.0f);
      pageSwitchAnimation.playTogether(ObjectAnimator.ofFloat(listView[1],View.TRANSLATION_X,0,AndroidUtilities.dp(56)),ObjectAnimator.ofFloat(listView[1],View.ALPHA,1.0f,0.0f));
    }
    pageSwitchAnimation.setDuration(150);
    pageSwitchAnimation.setInterpolator(interpolator);
    pageSwitchAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        listView[1].setVisibility(View.GONE);
        listView[index].setBackgroundDrawable(null);
        if (Build.VERSION.SDK_INT >= 18) {
          listView[index].setLayerType(View.LAYER_TYPE_NONE,null);
        }
        pageSwitchAnimation=null;
      }
    }
);
    pageSwitchAnimation.start();
  }
  headerView.invalidate();
  adapter[0].cleanup();
  int numBlocks=0;
  int count=currentPage.cached_page.blocks.size();
  for (int a=0; a < count; a++) {
    TLRPC.PageBlock block=currentPage.cached_page.blocks.get(a);
    if (a == 0) {
      block.first=true;
      if (block instanceof TLRPC.TL_pageBlockCover) {
        TLRPC.TL_pageBlockCover pageBlockCover=(TLRPC.TL_pageBlockCover)block;
        TLRPC.RichText caption=getBlockCaption(pageBlockCover,0);
        TLRPC.RichText credit=getBlockCaption(pageBlockCover,1);
        if ((caption != null && !(caption instanceof TLRPC.TL_textEmpty) || credit != null && !(credit instanceof TLRPC.TL_textEmpty)) && count > 1) {
          TLRPC.PageBlock next=currentPage.cached_page.blocks.get(1);
          if (next instanceof TLRPC.TL_pageBlockChannel) {
            channelBlock=(TLRPC.TL_pageBlockChannel)next;
          }
        }
      }
    }
 else     if (a == 1 && channelBlock != null) {
      continue;
    }
    adapter[0].addBlock(block,0,0,a == count - 1 ? a : 0);
  }
  adapter[0].notifyDataSetChanged();
  if (pagesStack.size() == 1 || order == -1) {
    SharedPreferences preferences=ApplicationLoader.applicationContext.getSharedPreferences("articles",Activity.MODE_PRIVATE);
    String key="article" + currentPage.id;
    int position=preferences.getInt(key,-1);
    int offset;
    if (preferences.getBoolean(key + "r",true) == AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y) {
      offset=preferences.getInt(key + "o",0) - listView[0].getPaddingTop();
    }
 else {
      offset=AndroidUtilities.dp(10);
    }
    if (position != -1) {
      layoutManager[0].scrollToPositionWithOffset(position,offset);
    }
  }
 else {
    layoutManager[0].scrollToPositionWithOffset(0,0);
  }
  checkScrollAnimated();
}
