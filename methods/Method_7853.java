public void setParentActivity(Activity activity,BaseFragment fragment){
  parentFragment=fragment;
  currentAccount=UserConfig.selectedAccount;
  leftImage.setCurrentAccount(currentAccount);
  rightImage.setCurrentAccount(currentAccount);
  centerImage.setCurrentAccount(currentAccount);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingProgressDidChanged);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingDidReset);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingPlayStateChanged);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingDidStart);
  NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.needSetDayNightTheme);
  if (parentActivity == activity) {
    updatePaintColors();
    return;
  }
  parentActivity=activity;
  SharedPreferences sharedPreferences=ApplicationLoader.applicationContext.getSharedPreferences("articles",Activity.MODE_PRIVATE);
  selectedFontSize=sharedPreferences.getInt("font_size",2);
  selectedFont=sharedPreferences.getInt("font_type",0);
  selectedColor=sharedPreferences.getInt("font_color",0);
  nightModeEnabled=sharedPreferences.getBoolean("nightModeEnabled",false);
  createPaint(false);
  backgroundPaint=new Paint();
  layerShadowDrawable=activity.getResources().getDrawable(R.drawable.layer_shadow);
  slideDotDrawable=activity.getResources().getDrawable(R.drawable.slide_dot_small);
  slideDotBigDrawable=activity.getResources().getDrawable(R.drawable.slide_dot_big);
  scrimPaint=new Paint();
  windowView=new WindowView(activity);
  windowView.setWillNotDraw(false);
  windowView.setClipChildren(true);
  windowView.setFocusable(false);
  containerView=new FrameLayout(activity){
    @Override protected boolean drawChild(    Canvas canvas,    View child,    long drawingTime){
      if (windowView.movingPage) {
        int width=getMeasuredWidth();
        int translationX=(int)listView[0].getTranslationX();
        int clipLeft=0;
        int clipRight=width;
        if (child == listView[1]) {
          clipRight=translationX;
        }
 else         if (child == listView[0]) {
          clipLeft=translationX;
        }
        final int restoreCount=canvas.save();
        canvas.clipRect(clipLeft,0,clipRight,getHeight());
        final boolean result=super.drawChild(canvas,child,drawingTime);
        canvas.restoreToCount(restoreCount);
        if (translationX != 0) {
          if (child == listView[0]) {
            final float alpha=Math.max(0,Math.min((width - translationX) / (float)AndroidUtilities.dp(20),1.0f));
            layerShadowDrawable.setBounds(translationX - layerShadowDrawable.getIntrinsicWidth(),child.getTop(),translationX,child.getBottom());
            layerShadowDrawable.setAlpha((int)(0xff * alpha));
            layerShadowDrawable.draw(canvas);
          }
 else           if (child == listView[1]) {
            float opacity=Math.min(0.8f,(width - translationX) / (float)width);
            if (opacity < 0) {
              opacity=0;
            }
            scrimPaint.setColor((int)(((0x99000000 & 0xff000000) >>> 24) * opacity) << 24);
            canvas.drawRect(clipLeft,0,clipRight,getHeight(),scrimPaint);
          }
        }
        return result;
      }
 else {
        return super.drawChild(canvas,child,drawingTime);
      }
    }
  }
;
  windowView.addView(containerView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT));
  containerView.setFitsSystemWindows(true);
  if (Build.VERSION.SDK_INT >= 21) {
    containerView.setOnApplyWindowInsetsListener((v,insets) -> {
      WindowInsets oldInsets=(WindowInsets)lastInsets;
      lastInsets=insets;
      if (oldInsets == null || !oldInsets.toString().equals(insets.toString())) {
        windowView.requestLayout();
      }
      if (Build.VERSION.SDK_INT >= 28) {
        DisplayCutout cutout=parentActivity.getWindow().getDecorView().getRootWindowInsets().getDisplayCutout();
        if (cutout != null) {
          List<Rect> rects=cutout.getBoundingRects();
          if (rects != null && !rects.isEmpty()) {
            hasCutout=rects.get(0).height() != 0;
          }
        }
      }
      return insets.consumeSystemWindowInsets();
    }
);
  }
  containerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN);
  photoContainerBackground=new View(activity);
  photoContainerBackground.setVisibility(View.INVISIBLE);
  photoContainerBackground.setBackgroundDrawable(photoBackgroundDrawable);
  windowView.addView(photoContainerBackground,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT));
  animatingImageView=new ClippingImageView(activity);
  animatingImageView.setAnimationValues(animationValues);
  animatingImageView.setVisibility(View.GONE);
  windowView.addView(animatingImageView,LayoutHelper.createFrame(40,40));
  photoContainerView=new FrameLayoutDrawer(activity){
    @Override protected void onLayout(    boolean changed,    int left,    int top,    int right,    int bottom){
      super.onLayout(changed,left,top,right,bottom);
      int y=bottom - top - captionTextView.getMeasuredHeight();
      int y2=bottom - top - groupedPhotosListView.getMeasuredHeight();
      if (bottomLayout.getVisibility() == VISIBLE) {
        y-=bottomLayout.getMeasuredHeight();
        y2-=bottomLayout.getMeasuredHeight();
      }
      if (!groupedPhotosListView.currentPhotos.isEmpty()) {
        y-=groupedPhotosListView.getMeasuredHeight();
      }
      captionTextView.layout(0,y,captionTextView.getMeasuredWidth(),y + captionTextView.getMeasuredHeight());
      captionTextViewNext.layout(0,y,captionTextViewNext.getMeasuredWidth(),y + captionTextViewNext.getMeasuredHeight());
      groupedPhotosListView.layout(0,y2,groupedPhotosListView.getMeasuredWidth(),y2 + groupedPhotosListView.getMeasuredHeight());
    }
  }
;
  photoContainerView.setVisibility(View.INVISIBLE);
  photoContainerView.setWillNotDraw(false);
  windowView.addView(photoContainerView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT));
  fullscreenVideoContainer=new FrameLayout(activity);
  fullscreenVideoContainer.setBackgroundColor(0xff000000);
  fullscreenVideoContainer.setVisibility(View.INVISIBLE);
  windowView.addView(fullscreenVideoContainer,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  fullscreenAspectRatioView=new AspectRatioFrameLayout(activity);
  fullscreenAspectRatioView.setVisibility(View.GONE);
  fullscreenVideoContainer.addView(fullscreenAspectRatioView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
  fullscreenTextureView=new TextureView(activity);
  listView=new RecyclerListView[2];
  adapter=new WebpageAdapter[2];
  layoutManager=new LinearLayoutManager[2];
  for (int i=0; i < listView.length; i++) {
    listView[i]=new RecyclerListView(activity){
      @Override protected void onLayout(      boolean changed,      int l,      int t,      int r,      int b){
        super.onLayout(changed,l,t,r,b);
        int count=getChildCount();
        for (int a=0; a < count; a++) {
          View child=getChildAt(a);
          if (child.getTag() instanceof Integer) {
            Integer tag=(Integer)child.getTag();
            if (tag == 90) {
              int bottom=child.getBottom();
              if (bottom < getMeasuredHeight()) {
                int height=getMeasuredHeight();
                child.layout(0,height - child.getMeasuredHeight(),child.getMeasuredWidth(),height);
                break;
              }
            }
          }
        }
      }
      @Override public boolean onInterceptTouchEvent(      MotionEvent e){
        if (pressedLinkOwnerLayout != null && pressedLink == null && (popupWindow == null || !popupWindow.isShowing()) && (e.getAction() == MotionEvent.ACTION_UP || e.getAction() == MotionEvent.ACTION_CANCEL)) {
          pressedLink=null;
          pressedLinkOwnerLayout=null;
          pressedLinkOwnerView=null;
        }
 else         if (pressedLinkOwnerLayout != null && pressedLink != null && e.getAction() == MotionEvent.ACTION_UP) {
          checkLayoutForLinks(e,pressedLinkOwnerView,pressedLinkOwnerLayout,0,0);
        }
        return super.onInterceptTouchEvent(e);
      }
      @Override public boolean onTouchEvent(      MotionEvent e){
        if (pressedLinkOwnerLayout != null && pressedLink == null && (popupWindow == null || !popupWindow.isShowing()) && (e.getAction() == MotionEvent.ACTION_UP || e.getAction() == MotionEvent.ACTION_CANCEL)) {
          pressedLink=null;
          pressedLinkOwnerLayout=null;
          pressedLinkOwnerView=null;
        }
        return super.onTouchEvent(e);
      }
      @Override public void setTranslationX(      float translationX){
        super.setTranslationX(translationX);
        if (windowView.movingPage) {
          containerView.invalidate();
          float progress=translationX / getMeasuredWidth();
          setCurrentHeaderHeight((int)(windowView.startMovingHeaderHeight + (AndroidUtilities.dp(56) - windowView.startMovingHeaderHeight) * progress));
        }
      }
    }
;
    ((DefaultItemAnimator)listView[i].getItemAnimator()).setDelayAnimations(false);
    listView[i].setLayoutManager(layoutManager[i]=new LinearLayoutManager(parentActivity,LinearLayoutManager.VERTICAL,false));
    WebpageAdapter webpageAdapter=adapter[i]=new WebpageAdapter(parentActivity);
    listView[i].setAdapter(webpageAdapter);
    listView[i].setClipToPadding(false);
    listView[i].setVisibility(i == 0 ? View.VISIBLE : View.GONE);
    listView[i].setPadding(0,AndroidUtilities.dp(56),0,0);
    listView[i].setTopGlowOffset(AndroidUtilities.dp(56));
    containerView.addView(listView[i],LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
    listView[i].setOnItemLongClickListener((view,position) -> {
      if (view instanceof BlockRelatedArticlesCell) {
        BlockRelatedArticlesCell cell=(BlockRelatedArticlesCell)view;
        showCopyPopup(cell.currentBlock.parent.articles.get(cell.currentBlock.num).url);
        return true;
      }
      return false;
    }
);
    listView[i].setOnItemClickListener((view,position) -> {
      if (position == webpageAdapter.localBlocks.size() && currentPage != null) {
        if (previewsReqId != 0) {
          return;
        }
        TLObject object=MessagesController.getInstance(currentAccount).getUserOrChat("previews");
        if (object instanceof TLRPC.TL_user) {
          openPreviewsChat((TLRPC.User)object,currentPage.id);
        }
 else {
          final int currentAccount=UserConfig.selectedAccount;
          final long pageId=currentPage.id;
          showProgressView(true,true);
          TLRPC.TL_contacts_resolveUsername req=new TLRPC.TL_contacts_resolveUsername();
          req.username="previews";
          previewsReqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
            if (previewsReqId == 0) {
              return;
            }
            previewsReqId=0;
            showProgressView(true,false);
            if (response != null) {
              TLRPC.TL_contacts_resolvedPeer res=(TLRPC.TL_contacts_resolvedPeer)response;
              MessagesController.getInstance(currentAccount).putUsers(res.users,false);
              MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,false,true);
              if (!res.users.isEmpty()) {
                openPreviewsChat(res.users.get(0),pageId);
              }
            }
          }
));
        }
      }
 else       if (position >= 0 && position < webpageAdapter.localBlocks.size()) {
        TLRPC.PageBlock pageBlock=webpageAdapter.localBlocks.get(position);
        TLRPC.PageBlock originalBlock=pageBlock;
        pageBlock=getLastNonListPageBlock(pageBlock);
        if (pageBlock instanceof TL_pageBlockDetailsChild) {
          TL_pageBlockDetailsChild detailsChild=(TL_pageBlockDetailsChild)pageBlock;
          pageBlock=detailsChild.block;
        }
        if (pageBlock instanceof TLRPC.TL_pageBlockChannel) {
          TLRPC.TL_pageBlockChannel pageBlockChannel=(TLRPC.TL_pageBlockChannel)pageBlock;
          MessagesController.getInstance(currentAccount).openByUserName(pageBlockChannel.channel.username,parentFragment,2);
          close(false,true);
        }
 else         if (pageBlock instanceof TL_pageBlockRelatedArticlesChild) {
          TL_pageBlockRelatedArticlesChild pageBlockRelatedArticlesChild=(TL_pageBlockRelatedArticlesChild)pageBlock;
          openWebpageUrl(pageBlockRelatedArticlesChild.parent.articles.get(pageBlockRelatedArticlesChild.num).url,null);
        }
 else         if (pageBlock instanceof TLRPC.TL_pageBlockDetails) {
          view=getLastNonListCell(view);
          if (!(view instanceof BlockDetailsCell)) {
            return;
          }
          pressedLinkOwnerLayout=null;
          pressedLinkOwnerView=null;
          int index=webpageAdapter.blocks.indexOf(originalBlock);
          if (index < 0) {
            return;
          }
          TLRPC.TL_pageBlockDetails pageBlockDetails=(TLRPC.TL_pageBlockDetails)pageBlock;
          pageBlockDetails.open=!pageBlockDetails.open;
          int oldCount=webpageAdapter.getItemCount();
          webpageAdapter.updateRows();
          int newCount=webpageAdapter.getItemCount();
          int changeCount=Math.abs(newCount - oldCount);
          BlockDetailsCell cell=(BlockDetailsCell)view;
          cell.arrow.setAnimationProgressAnimated(pageBlockDetails.open ? 0.0f : 1.0f);
          cell.invalidate();
          if (changeCount != 0) {
            if (pageBlockDetails.open) {
              webpageAdapter.notifyItemRangeInserted(position + 1,changeCount);
            }
 else {
              webpageAdapter.notifyItemRangeRemoved(position + 1,changeCount);
            }
          }
        }
      }
    }
);
    listView[i].setOnScrollListener(new RecyclerView.OnScrollListener(){
      @Override public void onScrolled(      RecyclerView recyclerView,      int dx,      int dy){
        if (recyclerView.getChildCount() == 0) {
          return;
        }
        headerView.invalidate();
        checkScroll(dy);
      }
    }
);
  }
  headerPaint.setColor(0xff000000);
  statusBarPaint.setColor(0xff000000);
  headerProgressPaint.setColor(0xff242426);
  headerView=new FrameLayout(activity){
    @Override protected void onDraw(    Canvas canvas){
      int width=getMeasuredWidth();
      int height=getMeasuredHeight();
      canvas.drawRect(0,0,width,height,headerPaint);
      if (layoutManager == null) {
        return;
      }
      int first=layoutManager[0].findFirstVisibleItemPosition();
      int last=layoutManager[0].findLastVisibleItemPosition();
      int count=layoutManager[0].getItemCount();
      View view;
      if (last >= count - 2) {
        view=layoutManager[0].findViewByPosition(count - 2);
      }
 else {
        view=layoutManager[0].findViewByPosition(first);
      }
      if (view == null) {
        return;
      }
      float itemProgress=width / (float)(count - 1);
      int childCount=layoutManager[0].getChildCount();
      float viewHeight=view.getMeasuredHeight();
      float viewProgress;
      if (last >= count - 2) {
        viewProgress=(count - 2 - first) * itemProgress * (listView[0].getMeasuredHeight() - view.getTop()) / viewHeight;
      }
 else {
        viewProgress=itemProgress * (1.0f - (Math.min(0,view.getTop() - listView[0].getPaddingTop()) + viewHeight) / viewHeight);
      }
      float progress=first * itemProgress + viewProgress;
      canvas.drawRect(0,0,progress,height,headerProgressPaint);
    }
  }
;
  headerView.setOnTouchListener((v,event) -> true);
  headerView.setWillNotDraw(false);
  containerView.addView(headerView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,56));
  backButton=new ImageView(activity);
  backButton.setScaleType(ImageView.ScaleType.CENTER);
  backDrawable=new BackDrawable(false);
  backDrawable.setAnimationTime(200.0f);
  backDrawable.setColor(0xffb3b3b3);
  backDrawable.setRotated(false);
  backButton.setImageDrawable(backDrawable);
  backButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
  headerView.addView(backButton,LayoutHelper.createFrame(54,56));
  backButton.setOnClickListener(v -> {
    close(true,true);
  }
);
  backButton.setContentDescription(LocaleController.getString("AccDescrGoBack",R.string.AccDescrGoBack));
  titleTextView=new SimpleTextView(activity);
  titleTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
  titleTextView.setTextSize(20);
  titleTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  titleTextView.setTextColor(0xffb3b3b3);
  titleTextView.setPivotX(0.0f);
  titleTextView.setPivotY(AndroidUtilities.dp(28));
  headerView.addView(titleTextView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,56,Gravity.LEFT | Gravity.TOP,72,0,48 * 2,0));
  lineProgressView=new LineProgressView(activity);
  lineProgressView.setProgressColor(0xffffffff);
  lineProgressView.setPivotX(0.0f);
  lineProgressView.setPivotY(AndroidUtilities.dp(2));
  headerView.addView(lineProgressView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,2,Gravity.LEFT | Gravity.BOTTOM,0,0,0,1));
  lineProgressTickRunnable=() -> {
    float progressLeft=0.7f - lineProgressView.getCurrentProgress();
    if (progressLeft > 0.0f) {
      float tick;
      if (progressLeft < 0.25f) {
        tick=0.01f;
      }
 else {
        tick=0.02f;
      }
      lineProgressView.setProgress(lineProgressView.getCurrentProgress() + tick,true);
      AndroidUtilities.runOnUIThread(lineProgressTickRunnable,100);
    }
  }
;
  LinearLayout settingsContainer=new LinearLayout(parentActivity);
  settingsContainer.setPadding(0,AndroidUtilities.dp(4),0,AndroidUtilities.dp(4));
  settingsContainer.setOrientation(LinearLayout.VERTICAL);
  for (int a=0; a < 3; a++) {
    colorCells[a]=new ColorCell(parentActivity);
switch (a) {
case 0:
      nightModeImageView=new ImageView(parentActivity);
    nightModeImageView.setScaleType(ImageView.ScaleType.CENTER);
  nightModeImageView.setImageResource(R.drawable.moon);
nightModeImageView.setColorFilter(new PorterDuffColorFilter(nightModeEnabled && selectedColor != 2 ? 0xff1495e9 : 0xffcccccc,PorterDuff.Mode.MULTIPLY));
nightModeImageView.setBackgroundDrawable(Theme.createSelectorDrawable(0x0f000000));
colorCells[a].addView(nightModeImageView,LayoutHelper.createFrame(48,48,Gravity.TOP | (LocaleController.isRTL ? Gravity.LEFT : Gravity.RIGHT)));
nightModeImageView.setOnClickListener(v -> {
nightModeEnabled=!nightModeEnabled;
ApplicationLoader.applicationContext.getSharedPreferences("articles",Activity.MODE_PRIVATE).edit().putBoolean("nightModeEnabled",nightModeEnabled).commit();
updateNightModeButton();
updatePaintColors();
for (int i=0; i < listView.length; i++) {
adapter[i].notifyDataSetChanged();
}
if (nightModeEnabled) {
showNightModeHint();
}
}
);
colorCells[a].setTextAndColor(LocaleController.getString("ColorWhite",R.string.ColorWhite),0xffffffff);
break;
case 1:
colorCells[a].setTextAndColor(LocaleController.getString("ColorSepia",R.string.ColorSepia),0xffeae5c9);
break;
case 2:
colorCells[a].setTextAndColor(LocaleController.getString("ColorDark",R.string.ColorDark),0xff232323);
break;
}
colorCells[a].select(a == selectedColor);
colorCells[a].setTag(a);
colorCells[a].setOnClickListener(v -> {
int num=(Integer)v.getTag();
selectedColor=num;
for (int a12=0; a12 < 3; a12++) {
colorCells[a12].select(a12 == num);
}
updateNightModeButton();
updatePaintColors();
for (int i=0; i < listView.length; i++) {
adapter[i].notifyDataSetChanged();
}
}
);
settingsContainer.addView(colorCells[a],LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,50));
}
updateNightModeButton();
View divider=new View(parentActivity);
divider.setBackgroundColor(0xffe0e0e0);
settingsContainer.addView(divider,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,1,15,4,15,4));
divider.getLayoutParams().height=1;
for (int a=0; a < 2; a++) {
fontCells[a]=new FontCell(parentActivity);
switch (a) {
case 0:
fontCells[a].setTextAndTypeface("Roboto",Typeface.DEFAULT);
break;
case 1:
fontCells[a].setTextAndTypeface("Serif",Typeface.SERIF);
break;
}
fontCells[a].select(a == selectedFont);
fontCells[a].setTag(a);
fontCells[a].setOnClickListener(v -> {
int num=(Integer)v.getTag();
selectedFont=num;
for (int a1=0; a1 < 2; a1++) {
fontCells[a1].select(a1 == num);
}
updatePaintFonts();
for (int i=0; i < listView.length; i++) {
adapter[i].notifyDataSetChanged();
}
}
);
settingsContainer.addView(fontCells[a],LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,50));
}
divider=new View(parentActivity);
divider.setBackgroundColor(0xffe0e0e0);
settingsContainer.addView(divider,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,1,15,4,15,4));
divider.getLayoutParams().height=1;
TextView textView=new TextView(parentActivity);
textView.setTextColor(0xff212121);
textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
textView.setLines(1);
textView.setMaxLines(1);
textView.setSingleLine(true);
textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
textView.setGravity((LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP);
textView.setText(LocaleController.getString("FontSize",R.string.FontSize));
settingsContainer.addView(textView,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,17,12,17,0));
SizeChooseView sizeChooseView=new SizeChooseView(parentActivity);
settingsContainer.addView(sizeChooseView,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,38,0,0,0,1));
settingsButton=new ActionBarMenuItem(parentActivity,null,Theme.ACTION_BAR_WHITE_SELECTOR_COLOR,0xffffffff);
settingsButton.setPopupAnimationEnabled(false);
settingsButton.setLayoutInScreen(true);
textView=new TextView(parentActivity);
textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
textView.setText("Aa");
textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
textView.setTextColor(0xffb3b3b3);
textView.setGravity(Gravity.CENTER);
textView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
settingsButton.addView(textView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
settingsButton.addSubItem(settingsContainer,AndroidUtilities.dp(220),LayoutHelper.WRAP_CONTENT);
settingsButton.redrawPopup(0xffffffff);
settingsButton.setContentDescription(LocaleController.getString("Settings",R.string.Settings));
headerView.addView(settingsButton,LayoutHelper.createFrame(48,56,Gravity.TOP | Gravity.RIGHT,0,0,56,0));
shareContainer=new FrameLayout(activity);
headerView.addView(shareContainer,LayoutHelper.createFrame(48,56,Gravity.TOP | Gravity.RIGHT));
shareContainer.setOnClickListener(v -> {
if (currentPage == null || parentActivity == null) {
return;
}
showDialog(new ShareAlert(parentActivity,null,currentPage.url,false,currentPage.url,true));
}
);
shareButton=new ImageView(activity);
shareButton.setScaleType(ImageView.ScaleType.CENTER);
shareButton.setImageResource(R.drawable.ic_share_article);
shareButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
shareButton.setContentDescription(LocaleController.getString("ShareFile",R.string.ShareFile));
shareContainer.addView(shareButton,LayoutHelper.createFrame(48,56));
progressView=new ContextProgressView(activity,2);
progressView.setVisibility(View.GONE);
shareContainer.addView(progressView,LayoutHelper.createFrame(48,56));
windowLayoutParams=new WindowManager.LayoutParams();
windowLayoutParams.height=WindowManager.LayoutParams.MATCH_PARENT;
windowLayoutParams.format=PixelFormat.TRANSLUCENT;
windowLayoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;
windowLayoutParams.gravity=Gravity.TOP | Gravity.LEFT;
windowLayoutParams.type=WindowManager.LayoutParams.LAST_APPLICATION_WINDOW;
if (Build.VERSION.SDK_INT >= 21) {
windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
if (Build.VERSION.SDK_INT >= 28) {
windowLayoutParams.layoutInDisplayCutoutMode=WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
}
}
 else {
windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
}
if (progressDrawables == null) {
progressDrawables=new Drawable[4];
progressDrawables[0]=parentActivity.getResources().getDrawable(R.drawable.circle_big);
progressDrawables[1]=parentActivity.getResources().getDrawable(R.drawable.cancel_big);
progressDrawables[2]=parentActivity.getResources().getDrawable(R.drawable.load_big);
progressDrawables[3]=parentActivity.getResources().getDrawable(R.drawable.play_big);
}
scroller=new Scroller(activity);
blackPaint.setColor(0xff000000);
actionBar=new ActionBar(activity);
actionBar.setBackgroundColor(Theme.ACTION_BAR_PHOTO_VIEWER_COLOR);
actionBar.setOccupyStatusBar(false);
actionBar.setTitleColor(0xffffffff);
actionBar.setItemsBackgroundColor(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR,false);
actionBar.setBackButtonImage(R.drawable.ic_ab_back);
actionBar.setTitle(LocaleController.formatString("Of",R.string.Of,1,1));
photoContainerView.addView(actionBar,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick(){
@Override public void onItemClick(int id){
if (id == -1) {
closePhoto(true);
}
 else if (id == gallery_menu_save) {
if (Build.VERSION.SDK_INT >= 23 && parentActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
parentActivity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},4);
return;
}
File f=getMediaFile(currentIndex);
if (f != null && f.exists()) {
MediaController.saveFile(f.toString(),parentActivity,isMediaVideo(currentIndex) ? 1 : 0,null,null);
}
 else {
AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
builder.setMessage(LocaleController.getString("PleaseDownload",R.string.PleaseDownload));
showDialog(builder.create());
}
}
 else if (id == gallery_menu_share) {
onSharePressed();
}
 else if (id == gallery_menu_openin) {
try {
AndroidUtilities.openForView(getMedia(currentIndex),parentActivity);
closePhoto(false);
}
 catch (Exception e) {
FileLog.e(e);
}
}
}
@Override public boolean canOpenMenu(){
File f=getMediaFile(currentIndex);
return f != null && f.exists();
}
}
);
ActionBarMenu menu=actionBar.createMenu();
menu.addItem(gallery_menu_share,R.drawable.share);
menuItem=menu.addItem(0,R.drawable.ic_ab_other);
menuItem.setLayoutInScreen(true);
menuItem.addSubItem(gallery_menu_openin,R.drawable.msg_openin,LocaleController.getString("OpenInExternalApp",R.string.OpenInExternalApp)).setColors(0xfffafafa,0xfffafafa);
menuItem.addSubItem(gallery_menu_save,R.drawable.msg_gallery,LocaleController.getString("SaveToGallery",R.string.SaveToGallery)).setColors(0xfffafafa,0xfffafafa);
menuItem.redrawPopup(0xf9222222);
bottomLayout=new FrameLayout(parentActivity);
bottomLayout.setBackgroundColor(0x7f000000);
photoContainerView.addView(bottomLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,48,Gravity.BOTTOM | Gravity.LEFT));
groupedPhotosListView=new GroupedPhotosListView(parentActivity);
photoContainerView.addView(groupedPhotosListView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,62,Gravity.BOTTOM | Gravity.LEFT,0,0,0,0));
groupedPhotosListView.setDelegate(new GroupedPhotosListView.GroupedPhotosListViewDelegate(){
@Override public int getCurrentIndex(){
return currentIndex;
}
@Override public int getCurrentAccount(){
return currentAccount;
}
@Override public int getAvatarsDialogId(){
return 0;
}
@Override public int getSlideshowMessageId(){
return 0;
}
@Override public ArrayList<ImageLocation> getImagesArrLocations(){
return null;
}
@Override public ArrayList<MessageObject> getImagesArr(){
return null;
}
@Override public ArrayList<TLRPC.PageBlock> getPageBlockArr(){
return imagesArr;
}
@Override public Object getParentObject(){
return currentPage;
}
@Override public void setCurrentIndex(int index){
currentIndex=-1;
if (currentThumb != null) {
currentThumb.release();
currentThumb=null;
}
setImageIndex(index,true);
}
}
);
captionTextViewNext=new TextView(activity);
captionTextViewNext.setMaxLines(10);
captionTextViewNext.setBackgroundColor(0x7f000000);
captionTextViewNext.setMovementMethod(new PhotoViewer.LinkMovementMethodMy());
captionTextViewNext.setPadding(AndroidUtilities.dp(20),AndroidUtilities.dp(8),AndroidUtilities.dp(20),AndroidUtilities.dp(8));
captionTextViewNext.setLinkTextColor(0xffffffff);
captionTextViewNext.setTextColor(0xffffffff);
captionTextViewNext.setHighlightColor(0x33ffffff);
captionTextViewNext.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
captionTextViewNext.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
captionTextViewNext.setVisibility(View.GONE);
photoContainerView.addView(captionTextViewNext,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.BOTTOM | Gravity.LEFT));
captionTextView=new TextView(activity);
captionTextView.setMaxLines(10);
captionTextView.setBackgroundColor(0x7f000000);
captionTextView.setMovementMethod(new PhotoViewer.LinkMovementMethodMy());
captionTextView.setPadding(AndroidUtilities.dp(20),AndroidUtilities.dp(8),AndroidUtilities.dp(20),AndroidUtilities.dp(8));
captionTextView.setLinkTextColor(0xffffffff);
captionTextView.setTextColor(0xffffffff);
captionTextView.setHighlightColor(0x33ffffff);
captionTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
captionTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
captionTextView.setVisibility(View.GONE);
photoContainerView.addView(captionTextView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.BOTTOM | Gravity.LEFT));
radialProgressViews[0]=new RadialProgressView(activity,photoContainerView);
radialProgressViews[0].setBackgroundState(0,false);
radialProgressViews[1]=new RadialProgressView(activity,photoContainerView);
radialProgressViews[1].setBackgroundState(0,false);
radialProgressViews[2]=new RadialProgressView(activity,photoContainerView);
radialProgressViews[2].setBackgroundState(0,false);
videoPlayerSeekbar=new SeekBar(activity);
videoPlayerSeekbar.setColors(0x66ffffff,0x66ffffff,0xffd5d0d7,0xffffffff,0xffffffff);
videoPlayerSeekbar.setDelegate(progress -> {
if (videoPlayer != null) {
videoPlayer.seekTo((int)(progress * videoPlayer.getDuration()));
}
}
);
videoPlayerControlFrameLayout=new FrameLayout(activity){
@Override public boolean onTouchEvent(MotionEvent event){
int x=(int)event.getX();
int y=(int)event.getY();
if (videoPlayerSeekbar.onTouch(event.getAction(),event.getX() - AndroidUtilities.dp(48),event.getY())) {
getParent().requestDisallowInterceptTouchEvent(true);
invalidate();
return true;
}
return super.onTouchEvent(event);
}
@Override protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
super.onMeasure(widthMeasureSpec,heightMeasureSpec);
long duration;
if (videoPlayer != null) {
duration=videoPlayer.getDuration();
if (duration == C.TIME_UNSET) {
duration=0;
}
}
 else {
duration=0;
}
duration/=1000;
int size=(int)Math.ceil(videoPlayerTime.getPaint().measureText(String.format("%02d:%02d / %02d:%02d",duration / 60,duration % 60,duration / 60,duration % 60)));
videoPlayerSeekbar.setSize(getMeasuredWidth() - AndroidUtilities.dp(48 + 16) - size,getMeasuredHeight());
}
@Override protected void onLayout(boolean changed,int left,int top,int right,int bottom){
super.onLayout(changed,left,top,right,bottom);
float progress=0;
if (videoPlayer != null) {
progress=videoPlayer.getCurrentPosition() / (float)videoPlayer.getDuration();
}
videoPlayerSeekbar.setProgress(progress);
}
@Override protected void onDraw(Canvas canvas){
canvas.save();
canvas.translate(AndroidUtilities.dp(48),0);
videoPlayerSeekbar.draw(canvas);
canvas.restore();
}
}
;
videoPlayerControlFrameLayout.setWillNotDraw(false);
bottomLayout.addView(videoPlayerControlFrameLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT));
videoPlayButton=new ImageView(activity);
videoPlayButton.setScaleType(ImageView.ScaleType.CENTER);
videoPlayerControlFrameLayout.addView(videoPlayButton,LayoutHelper.createFrame(48,48,Gravity.LEFT | Gravity.TOP));
videoPlayButton.setOnClickListener(v -> {
if (videoPlayer != null) {
if (isPlaying) {
videoPlayer.pause();
}
 else {
videoPlayer.play();
}
}
}
);
videoPlayerTime=new TextView(activity);
videoPlayerTime.setTextColor(0xffffffff);
videoPlayerTime.setGravity(Gravity.CENTER_VERTICAL);
videoPlayerTime.setTextSize(TypedValue.COMPLEX_UNIT_DIP,13);
videoPlayerControlFrameLayout.addView(videoPlayerTime,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.MATCH_PARENT,Gravity.RIGHT | Gravity.TOP,0,0,8,0));
gestureDetector=new GestureDetector(activity,this);
gestureDetector.setOnDoubleTapListener(this);
centerImage.setParentView(photoContainerView);
centerImage.setCrossfadeAlpha((byte)2);
centerImage.setInvalidateAll(true);
leftImage.setParentView(photoContainerView);
leftImage.setCrossfadeAlpha((byte)2);
leftImage.setInvalidateAll(true);
rightImage.setParentView(photoContainerView);
rightImage.setCrossfadeAlpha((byte)2);
rightImage.setInvalidateAll(true);
updatePaintColors();
}
