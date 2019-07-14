private boolean open(final MessageObject messageObject,TLRPC.WebPage webpage,String url,boolean first){
  if (parentActivity == null || isVisible && !collapsed || messageObject == null && webpage == null) {
    return false;
  }
  if (messageObject != null) {
    webpage=messageObject.messageOwner.media.webpage;
  }
  String anchor=null;
  if (messageObject != null) {
    webpage=messageObject.messageOwner.media.webpage;
    int index;
    for (int a=0; a < messageObject.messageOwner.entities.size(); a++) {
      TLRPC.MessageEntity entity=messageObject.messageOwner.entities.get(a);
      if (entity instanceof TLRPC.TL_messageEntityUrl) {
        try {
          url=messageObject.messageOwner.message.substring(entity.offset,entity.offset + entity.length).toLowerCase();
          String webPageUrl;
          if (!TextUtils.isEmpty(webpage.cached_page.url)) {
            webPageUrl=webpage.cached_page.url.toLowerCase();
          }
 else {
            webPageUrl=webpage.url.toLowerCase();
          }
          if (url.contains(webPageUrl) || webPageUrl.contains(url)) {
            if ((index=url.lastIndexOf('#')) != -1) {
              anchor=url.substring(index + 1);
            }
            break;
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
  }
 else   if (url != null) {
    int index;
    if ((index=url.lastIndexOf('#')) != -1) {
      anchor=url.substring(index + 1);
    }
  }
  pagesStack.clear();
  collapsed=false;
  backDrawable.setRotation(0,false);
  containerView.setTranslationX(0);
  containerView.setTranslationY(0);
  listView[0].setTranslationY(0);
  listView[0].setTranslationX(0.0f);
  listView[1].setTranslationX(0.0f);
  listView[0].setAlpha(1.0f);
  windowView.setInnerTranslationX(0);
  actionBar.setVisibility(View.GONE);
  bottomLayout.setVisibility(View.GONE);
  captionTextView.setVisibility(View.GONE);
  captionTextViewNext.setVisibility(View.GONE);
  layoutManager[0].scrollToPositionWithOffset(0,0);
  if (first) {
    setCurrentHeaderHeight(AndroidUtilities.dp(56));
  }
 else {
    checkScrollAnimated();
  }
  boolean scrolledToAnchor=addPageToStack(webpage,anchor,0);
  if (first) {
    final String anchorFinal=!scrolledToAnchor && anchor != null ? anchor : null;
    TLRPC.TL_messages_getWebPage req=new TLRPC.TL_messages_getWebPage();
    req.url=webpage.url;
    if (webpage.cached_page instanceof TLRPC.TL_pagePart_layer82 || webpage.cached_page.part) {
      req.hash=0;
    }
 else {
      req.hash=webpage.hash;
    }
    final TLRPC.WebPage webPageFinal=webpage;
    final int currentAccount=UserConfig.selectedAccount;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (response instanceof TLRPC.TL_webPage) {
        final TLRPC.TL_webPage webPage=(TLRPC.TL_webPage)response;
        if (webPage.cached_page == null) {
          return;
        }
        AndroidUtilities.runOnUIThread(() -> {
          if (!pagesStack.isEmpty() && pagesStack.get(0) == webPageFinal && webPage.cached_page != null) {
            if (messageObject != null) {
              messageObject.messageOwner.media.webpage=webPage;
            }
            pagesStack.set(0,webPage);
            if (pagesStack.size() == 1) {
              currentPage=webPage;
              ApplicationLoader.applicationContext.getSharedPreferences("articles",Activity.MODE_PRIVATE).edit().remove("article" + currentPage.id).commit();
              updateInterfaceForCurrentPage(0);
              if (anchorFinal != null) {
                scrollToAnchor(anchorFinal);
              }
            }
          }
        }
);
        LongSparseArray<TLRPC.WebPage> webpages=new LongSparseArray<>(1);
        webpages.put(webPage.id,webPage);
        MessagesStorage.getInstance(currentAccount).putWebPages(webpages);
      }
    }
);
  }
  lastInsets=null;
  if (!isVisible) {
    WindowManager wm=(WindowManager)parentActivity.getSystemService(Context.WINDOW_SERVICE);
    if (attachedToWindow) {
      try {
        wm.removeView(windowView);
      }
 catch (      Exception e) {
      }
    }
    try {
      if (Build.VERSION.SDK_INT >= 21) {
        windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
        if (Build.VERSION.SDK_INT >= 28) {
          windowLayoutParams.layoutInDisplayCutoutMode=WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
      }
      windowLayoutParams.flags|=WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
      windowView.setFocusable(false);
      containerView.setFocusable(false);
      wm.addView(windowView,windowLayoutParams);
    }
 catch (    Exception e) {
      FileLog.e(e);
      return false;
    }
  }
 else {
    windowLayoutParams.flags&=~WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
    WindowManager wm=(WindowManager)parentActivity.getSystemService(Context.WINDOW_SERVICE);
    wm.updateViewLayout(windowView,windowLayoutParams);
  }
  isVisible=true;
  animationInProgress=1;
  windowView.setAlpha(0);
  containerView.setAlpha(0);
  final AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.playTogether(ObjectAnimator.ofFloat(windowView,View.ALPHA,0,1.0f),ObjectAnimator.ofFloat(containerView,View.ALPHA,0.0f,1.0f),ObjectAnimator.ofFloat(windowView,View.TRANSLATION_X,AndroidUtilities.dp(56),0));
  animationEndRunnable=() -> {
    if (containerView == null || windowView == null) {
      return;
    }
    if (Build.VERSION.SDK_INT >= 18) {
      containerView.setLayerType(View.LAYER_TYPE_NONE,null);
    }
    animationInProgress=0;
    AndroidUtilities.hideKeyboard(parentActivity.getCurrentFocus());
  }
;
  animatorSet.setDuration(150);
  animatorSet.setInterpolator(interpolator);
  animatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      AndroidUtilities.runOnUIThread(() -> {
        NotificationCenter.getInstance(currentAccount).setAnimationInProgress(false);
        if (animationEndRunnable != null) {
          animationEndRunnable.run();
          animationEndRunnable=null;
        }
      }
);
    }
  }
);
  transitionAnimationStartTime=System.currentTimeMillis();
  AndroidUtilities.runOnUIThread(() -> {
    NotificationCenter.getInstance(currentAccount).setAllowedNotificationsDutingAnimation(new int[]{NotificationCenter.dialogsNeedReload,NotificationCenter.closeChats});
    NotificationCenter.getInstance(currentAccount).setAnimationInProgress(true);
    animatorSet.start();
  }
);
  if (Build.VERSION.SDK_INT >= 18) {
    containerView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
  }
  return true;
}
