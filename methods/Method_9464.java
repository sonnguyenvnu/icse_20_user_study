public void setParentActivity(final Activity activity){
  currentAccount=UserConfig.selectedAccount;
  centerImage.setCurrentAccount(currentAccount);
  leftImage.setCurrentAccount(currentAccount);
  rightImage.setCurrentAccount(currentAccount);
  if (parentActivity == activity) {
    return;
  }
  parentActivity=activity;
  actvityContext=new ContextThemeWrapper(parentActivity,R.style.Theme_TMessages);
  if (progressDrawables == null) {
    progressDrawables=new Drawable[4];
    progressDrawables[0]=parentActivity.getResources().getDrawable(R.drawable.circle_big);
    progressDrawables[1]=parentActivity.getResources().getDrawable(R.drawable.cancel_big);
    progressDrawables[2]=parentActivity.getResources().getDrawable(R.drawable.load_big);
    progressDrawables[3]=parentActivity.getResources().getDrawable(R.drawable.play_big);
  }
  scroller=new Scroller(activity);
  windowView=new FrameLayout(activity){
    @Override public boolean onInterceptTouchEvent(    MotionEvent ev){
      return isVisible && super.onInterceptTouchEvent(ev);
    }
    @Override public boolean onTouchEvent(    MotionEvent event){
      return isVisible && PhotoViewer.this.onTouchEvent(event);
    }
    @Override protected boolean drawChild(    Canvas canvas,    View child,    long drawingTime){
      boolean result;
      try {
        result=super.drawChild(canvas,child,drawingTime);
      }
 catch (      Throwable ignore) {
        result=false;
      }
      if (Build.VERSION.SDK_INT >= 21 && child == animatingImageView && lastInsets != null) {
        WindowInsets insets=(WindowInsets)lastInsets;
        canvas.drawRect(0,getMeasuredHeight(),getMeasuredWidth(),getMeasuredHeight() + insets.getSystemWindowInsetBottom(),blackPaint);
      }
      return result;
    }
    @Override protected void onMeasure(    int widthMeasureSpec,    int heightMeasureSpec){
      int widthSize=MeasureSpec.getSize(widthMeasureSpec);
      int heightSize=MeasureSpec.getSize(heightMeasureSpec);
      if (Build.VERSION.SDK_INT >= 21 && lastInsets != null) {
        WindowInsets insets=(WindowInsets)lastInsets;
        if (AndroidUtilities.incorrectDisplaySizeFix) {
          if (heightSize > AndroidUtilities.displaySize.y) {
            heightSize=AndroidUtilities.displaySize.y;
          }
          heightSize+=AndroidUtilities.statusBarHeight;
        }
        heightSize-=insets.getSystemWindowInsetBottom();
      }
 else {
        if (heightSize > AndroidUtilities.displaySize.y) {
          heightSize=AndroidUtilities.displaySize.y;
        }
      }
      setMeasuredDimension(widthSize,heightSize);
      ViewGroup.LayoutParams layoutParams=animatingImageView.getLayoutParams();
      animatingImageView.measure(MeasureSpec.makeMeasureSpec(layoutParams.width,MeasureSpec.AT_MOST),MeasureSpec.makeMeasureSpec(layoutParams.height,MeasureSpec.AT_MOST));
      containerView.measure(MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.EXACTLY));
    }
    @SuppressWarnings("DrawAllocation") @Override protected void onLayout(    boolean changed,    int left,    int top,    int right,    int bottom){
      int x=0;
      if (Build.VERSION.SDK_INT >= 21 && lastInsets != null) {
      }
      animatingImageView.layout(x,0,x + animatingImageView.getMeasuredWidth(),animatingImageView.getMeasuredHeight());
      containerView.layout(x,0,x + containerView.getMeasuredWidth(),containerView.getMeasuredHeight());
      wasLayout=true;
      if (changed) {
        if (!dontResetZoomOnFirstLayout) {
          scale=1;
          translationX=0;
          translationY=0;
          updateMinMax(scale);
        }
        if (checkImageView != null) {
          checkImageView.post(() -> {
            LayoutParams layoutParams=(LayoutParams)checkImageView.getLayoutParams();
            WindowManager manager=(WindowManager)ApplicationLoader.applicationContext.getSystemService(Activity.WINDOW_SERVICE);
            int rotation=manager.getDefaultDisplay().getRotation();
            layoutParams.topMargin=(ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(40)) / 2 + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
            checkImageView.setLayoutParams(layoutParams);
            layoutParams=(LayoutParams)photosCounterView.getLayoutParams();
            layoutParams.topMargin=(ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(40)) / 2 + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
            photosCounterView.setLayoutParams(layoutParams);
          }
);
        }
      }
      if (dontResetZoomOnFirstLayout) {
        setScaleToFill();
        dontResetZoomOnFirstLayout=false;
      }
    }
    @Override protected void onAttachedToWindow(){
      super.onAttachedToWindow();
      attachedToWindow=true;
    }
    @Override protected void onDetachedFromWindow(){
      super.onDetachedFromWindow();
      attachedToWindow=false;
      wasLayout=false;
    }
    @Override public boolean dispatchKeyEventPreIme(    KeyEvent event){
      if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
        if (captionEditText.isPopupShowing() || captionEditText.isKeyboardVisible()) {
          closeCaptionEnter(false);
          return false;
        }
        PhotoViewer.getInstance().closePhoto(true,false);
        return true;
      }
      return super.dispatchKeyEventPreIme(event);
    }
    @Override public ActionMode startActionModeForChild(    View originalView,    ActionMode.Callback callback,    int type){
      if (Build.VERSION.SDK_INT >= 23) {
        View view=parentActivity.findViewById(android.R.id.content);
        if (view instanceof ViewGroup) {
          try {
            return ((ViewGroup)view).startActionModeForChild(originalView,callback,type);
          }
 catch (          Throwable e) {
            FileLog.e(e);
          }
        }
      }
      return super.startActionModeForChild(originalView,callback,type);
    }
  }
;
  windowView.setBackgroundDrawable(backgroundDrawable);
  windowView.setClipChildren(true);
  windowView.setFocusable(false);
  animatingImageView=new ClippingImageView(activity);
  animatingImageView.setAnimationValues(animationValues);
  windowView.addView(animatingImageView,LayoutHelper.createFrame(40,40));
  containerView=new FrameLayoutDrawer(activity);
  containerView.setFocusable(false);
  windowView.addView(containerView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT));
  if (Build.VERSION.SDK_INT >= 21) {
    containerView.setFitsSystemWindows(true);
    containerView.setOnApplyWindowInsetsListener((v,insets) -> {
      WindowInsets oldInsets=(WindowInsets)lastInsets;
      lastInsets=insets;
      if (oldInsets == null || !oldInsets.toString().equals(insets.toString())) {
        if (animationInProgress == 1) {
          animatingImageView.setTranslationX(animatingImageView.getTranslationX() - getLeftInset());
          animationValues[0][2]=animatingImageView.getTranslationX();
        }
        windowView.requestLayout();
      }
      containerView.setPadding(insets.getSystemWindowInsetLeft(),0,insets.getSystemWindowInsetRight(),0);
      return insets.consumeSystemWindowInsets();
    }
);
    containerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
  }
  windowLayoutParams=new WindowManager.LayoutParams();
  windowLayoutParams.height=WindowManager.LayoutParams.MATCH_PARENT;
  windowLayoutParams.format=PixelFormat.TRANSLUCENT;
  windowLayoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;
  windowLayoutParams.gravity=Gravity.TOP | Gravity.LEFT;
  windowLayoutParams.type=WindowManager.LayoutParams.LAST_APPLICATION_WINDOW;
  if (Build.VERSION.SDK_INT >= 28) {
    windowLayoutParams.layoutInDisplayCutoutMode=WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
  }
  if (Build.VERSION.SDK_INT >= 21) {
    windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
  }
 else {
    windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
  }
  actionBar=new ActionBar(activity){
    @Override public void setAlpha(    float alpha){
      super.setAlpha(alpha);
      containerView.invalidate();
    }
  }
;
  actionBar.setTitleColor(0xffffffff);
  actionBar.setSubtitleColor(0xffffffff);
  actionBar.setBackgroundColor(Theme.ACTION_BAR_PHOTO_VIEWER_COLOR);
  actionBar.setOccupyStatusBar(Build.VERSION.SDK_INT >= 21);
  actionBar.setItemsBackgroundColor(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR,false);
  actionBar.setBackButtonImage(R.drawable.ic_ab_back);
  actionBar.setTitle(LocaleController.formatString("Of",R.string.Of,1,1));
  containerView.addView(actionBar,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick(){
    @Override public void onItemClick(    int id){
      if (id == -1) {
        if (needCaptionLayout && (captionEditText.isPopupShowing() || captionEditText.isKeyboardVisible())) {
          closeCaptionEnter(false);
          return;
        }
        closePhoto(true,false);
      }
 else       if (id == gallery_menu_save) {
        if (Build.VERSION.SDK_INT >= 23 && parentActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
          parentActivity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},4);
          return;
        }
        File f=null;
        if (currentMessageObject != null) {
          if (currentMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && currentMessageObject.messageOwner.media.webpage != null && currentMessageObject.messageOwner.media.webpage.document == null) {
            TLObject fileLocation=getFileLocation(currentIndex,null);
            f=FileLoader.getPathToAttach(fileLocation,true);
          }
 else {
            f=FileLoader.getPathToMessage(currentMessageObject.messageOwner);
          }
        }
 else         if (currentFileLocation != null) {
          f=FileLoader.getPathToAttach(currentFileLocation.location,avatarsDialogId != 0 || isEvent);
        }
        if (f != null && f.exists()) {
          MediaController.saveFile(f.toString(),parentActivity,currentMessageObject != null && currentMessageObject.isVideo() ? 1 : 0,null,null);
        }
 else {
          showDownloadAlert();
        }
      }
 else       if (id == gallery_menu_showall) {
        if (currentDialogId != 0) {
          disableShowCheck=true;
          Bundle args2=new Bundle();
          args2.putLong("dialog_id",currentDialogId);
          MediaActivity mediaActivity=new MediaActivity(args2,new int[]{-1,-1,-1,-1,-1},null,sharedMediaType);
          if (parentChatActivity != null) {
            mediaActivity.setChatInfo(parentChatActivity.getCurrentChatInfo());
          }
          closePhoto(false,false);
          ((LaunchActivity)parentActivity).presentFragment(mediaActivity,false,true);
        }
      }
 else       if (id == gallery_menu_showinchat) {
        if (currentMessageObject == null) {
          return;
        }
        Bundle args=new Bundle();
        int lower_part=(int)currentDialogId;
        int high_id=(int)(currentDialogId >> 32);
        if (lower_part != 0) {
          if (high_id == 1) {
            args.putInt("chat_id",lower_part);
          }
 else {
            if (lower_part > 0) {
              args.putInt("user_id",lower_part);
            }
 else             if (lower_part < 0) {
              TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lower_part);
              if (chat != null && chat.migrated_to != null) {
                args.putInt("migrated_to",lower_part);
                lower_part=-chat.migrated_to.channel_id;
              }
              args.putInt("chat_id",-lower_part);
            }
          }
        }
 else {
          args.putInt("enc_id",high_id);
        }
        args.putInt("message_id",currentMessageObject.getId());
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats);
        LaunchActivity launchActivity=(LaunchActivity)parentActivity;
        boolean remove=launchActivity.getMainFragmentsCount() > 1 || AndroidUtilities.isTablet();
        launchActivity.presentFragment(new ChatActivity(args),remove,true);
        currentMessageObject=null;
        closePhoto(false,false);
      }
 else       if (id == gallery_menu_send) {
        if (currentMessageObject == null || parentActivity == null) {
          return;
        }
        ((LaunchActivity)parentActivity).switchToAccount(currentMessageObject.currentAccount,true);
        Bundle args=new Bundle();
        args.putBoolean("onlySelect",true);
        args.putInt("dialogsType",3);
        DialogsActivity fragment=new DialogsActivity(args);
        final ArrayList<MessageObject> fmessages=new ArrayList<>();
        fmessages.add(currentMessageObject);
        fragment.setDelegate((fragment1,dids,message,param) -> {
          if (dids.size() > 1 || dids.get(0) == UserConfig.getInstance(currentAccount).getClientUserId() || message != null) {
            for (int a=0; a < dids.size(); a++) {
              long did=dids.get(a);
              if (message != null) {
                SendMessagesHelper.getInstance(currentAccount).sendMessage(message.toString(),did,null,null,true,null,null,null);
              }
              SendMessagesHelper.getInstance(currentAccount).sendMessage(fmessages,did);
            }
            fragment1.finishFragment();
          }
 else {
            long did=dids.get(0);
            int lower_part=(int)did;
            int high_part=(int)(did >> 32);
            Bundle args1=new Bundle();
            args1.putBoolean("scrollToTopOnResume",true);
            if (lower_part != 0) {
              if (lower_part > 0) {
                args1.putInt("user_id",lower_part);
              }
 else               if (lower_part < 0) {
                args1.putInt("chat_id",-lower_part);
              }
            }
 else {
              args1.putInt("enc_id",high_part);
            }
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats);
            ChatActivity chatActivity=new ChatActivity(args1);
            if (((LaunchActivity)parentActivity).presentFragment(chatActivity,true,false)) {
              chatActivity.showFieldPanelForForward(true,fmessages);
            }
 else {
              fragment1.finishFragment();
            }
          }
        }
);
        ((LaunchActivity)parentActivity).presentFragment(fragment,false,true);
        closePhoto(false,false);
      }
 else       if (id == gallery_menu_delete) {
        if (parentActivity == null || placeProvider == null) {
          return;
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
        String text=placeProvider.getDeleteMessageString();
        if (text != null) {
          builder.setMessage(text);
        }
 else         if (currentMessageObject != null && currentMessageObject.isVideo()) {
          builder.setMessage(LocaleController.formatString("AreYouSureDeleteVideo",R.string.AreYouSureDeleteVideo));
        }
 else         if (currentMessageObject != null && currentMessageObject.isGif()) {
          builder.setMessage(LocaleController.formatString("AreYouSureDeleteGIF",R.string.AreYouSureDeleteGIF));
        }
 else {
          builder.setMessage(LocaleController.formatString("AreYouSureDeletePhoto",R.string.AreYouSureDeletePhoto));
        }
        builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
        final boolean[] deleteForAll=new boolean[1];
        if (currentMessageObject != null) {
          int lower_id=(int)currentMessageObject.getDialogId();
          if (lower_id != 0) {
            TLRPC.Chat currentChat;
            TLRPC.User currentUser;
            if (lower_id > 0) {
              currentUser=MessagesController.getInstance(currentAccount).getUser(lower_id);
              currentChat=null;
            }
 else {
              currentUser=null;
              currentChat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
            }
            if (currentUser != null || !ChatObject.isChannel(currentChat)) {
              boolean hasOutgoing=false;
              int currentDate=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
              int revokeTimeLimit;
              if (currentUser != null) {
                revokeTimeLimit=MessagesController.getInstance(currentAccount).revokeTimePmLimit;
              }
 else {
                revokeTimeLimit=MessagesController.getInstance(currentAccount).revokeTimeLimit;
              }
              if (currentUser != null && currentUser.id != UserConfig.getInstance(currentAccount).getClientUserId() || currentChat != null) {
                if ((currentMessageObject.messageOwner.action == null || currentMessageObject.messageOwner.action instanceof TLRPC.TL_messageActionEmpty) && currentMessageObject.isOut() && (currentDate - currentMessageObject.messageOwner.date) <= revokeTimeLimit) {
                  FrameLayout frameLayout=new FrameLayout(parentActivity);
                  CheckBoxCell cell=new CheckBoxCell(parentActivity,1);
                  cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
                  if (currentChat != null) {
                    cell.setText(LocaleController.getString("DeleteForAll",R.string.DeleteForAll),"",false,false);
                  }
 else {
                    cell.setText(LocaleController.formatString("DeleteForUser",R.string.DeleteForUser,UserObject.getFirstName(currentUser)),"",false,false);
                  }
                  cell.setPadding(LocaleController.isRTL ? AndroidUtilities.dp(16) : AndroidUtilities.dp(8),0,LocaleController.isRTL ? AndroidUtilities.dp(8) : AndroidUtilities.dp(16),0);
                  frameLayout.addView(cell,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,48,Gravity.TOP | Gravity.LEFT,0,0,0,0));
                  cell.setOnClickListener(v -> {
                    CheckBoxCell cell1=(CheckBoxCell)v;
                    deleteForAll[0]=!deleteForAll[0];
                    cell1.setChecked(deleteForAll[0],true);
                  }
);
                  builder.setView(frameLayout);
                }
              }
            }
          }
        }
        builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
          if (!imagesArr.isEmpty()) {
            if (currentIndex < 0 || currentIndex >= imagesArr.size()) {
              return;
            }
            MessageObject obj=imagesArr.get(currentIndex);
            if (obj.isSent()) {
              closePhoto(false,false);
              ArrayList<Integer> arr=new ArrayList<>();
              if (slideshowMessageId != 0) {
                arr.add(slideshowMessageId);
              }
 else {
                arr.add(obj.getId());
              }
              ArrayList<Long> random_ids=null;
              TLRPC.EncryptedChat encryptedChat=null;
              if ((int)obj.getDialogId() == 0 && obj.messageOwner.random_id != 0) {
                random_ids=new ArrayList<>();
                random_ids.add(obj.messageOwner.random_id);
                encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat((int)(obj.getDialogId() >> 32));
              }
              MessagesController.getInstance(currentAccount).deleteMessages(arr,random_ids,encryptedChat,obj.messageOwner.to_id.channel_id,deleteForAll[0]);
            }
          }
 else           if (!avatarsArr.isEmpty()) {
            if (currentIndex < 0 || currentIndex >= avatarsArr.size()) {
              return;
            }
            TLRPC.Photo photo=avatarsArr.get(currentIndex);
            ImageLocation currentLocation=imagesArrLocations.get(currentIndex);
            if (photo instanceof TLRPC.TL_photoEmpty) {
              photo=null;
            }
            boolean current=false;
            if (currentUserAvatarLocation != null) {
              if (photo != null) {
                for (                TLRPC.PhotoSize size : photo.sizes) {
                  if (size.location.local_id == currentUserAvatarLocation.location.local_id && size.location.volume_id == currentUserAvatarLocation.location.volume_id) {
                    current=true;
                    break;
                  }
                }
              }
 else               if (currentLocation.location.local_id == currentUserAvatarLocation.location.local_id && currentLocation.location.volume_id == currentUserAvatarLocation.location.volume_id) {
                current=true;
              }
            }
            if (current) {
              MessagesController.getInstance(currentAccount).deleteUserPhoto(null);
              closePhoto(false,false);
            }
 else             if (photo != null) {
              TLRPC.TL_inputPhoto inputPhoto=new TLRPC.TL_inputPhoto();
              inputPhoto.id=photo.id;
              inputPhoto.access_hash=photo.access_hash;
              inputPhoto.file_reference=photo.file_reference;
              if (inputPhoto.file_reference == null) {
                inputPhoto.file_reference=new byte[0];
              }
              MessagesController.getInstance(currentAccount).deleteUserPhoto(inputPhoto);
              MessagesStorage.getInstance(currentAccount).clearUserPhoto(avatarsDialogId,photo.id);
              imagesArrLocations.remove(currentIndex);
              imagesArrLocationsSizes.remove(currentIndex);
              avatarsArr.remove(currentIndex);
              if (imagesArrLocations.isEmpty()) {
                closePhoto(false,false);
              }
 else {
                int index=currentIndex;
                if (index >= avatarsArr.size()) {
                  index=avatarsArr.size() - 1;
                }
                currentIndex=-1;
                setImageIndex(index,true);
              }
            }
          }
 else           if (!secureDocuments.isEmpty()) {
            if (placeProvider == null) {
              return;
            }
            secureDocuments.remove(currentIndex);
            placeProvider.deleteImageAtIndex(currentIndex);
            if (secureDocuments.isEmpty()) {
              closePhoto(false,false);
            }
 else {
              int index=currentIndex;
              if (index >= secureDocuments.size()) {
                index=secureDocuments.size() - 1;
              }
              currentIndex=-1;
              setImageIndex(index,true);
            }
          }
        }
);
        builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
        showAlertDialog(builder);
      }
 else       if (id == gallery_menu_share) {
        onSharePressed();
      }
 else       if (id == gallery_menu_openin) {
        try {
          AndroidUtilities.openForView(currentMessageObject,parentActivity);
          closePhoto(false,false);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
 else       if (id == gallery_menu_masks) {
        if (parentActivity == null || currentMessageObject == null || currentMessageObject.messageOwner.media == null || currentMessageObject.messageOwner.media.photo == null) {
          return;
        }
        StickersAlert stickersAlert=new StickersAlert(parentActivity,currentMessageObject,currentMessageObject.messageOwner.media.photo);
        stickersAlert.show();
      }
 else       if (id == gallery_menu_pip) {
        if (pipItem.getAlpha() != 1.0f) {
          return;
        }
        switchToPip();
      }
 else       if (id == gallery_menu_cancel_loading) {
        if (currentMessageObject == null) {
          return;
        }
        FileLoader.getInstance(currentAccount).cancelLoadFile(currentMessageObject.getDocument());
        releasePlayer(false);
        bottomLayout.setTag(1);
        bottomLayout.setVisibility(View.VISIBLE);
      }
    }
    @Override public boolean canOpenMenu(){
      if (currentMessageObject != null) {
        File f=FileLoader.getPathToMessage(currentMessageObject.messageOwner);
        return f.exists();
      }
 else       if (currentFileLocation != null) {
        File f=FileLoader.getPathToAttach(getFileLocation(currentFileLocation),avatarsDialogId != 0 || isEvent);
        return f.exists();
      }
      return false;
    }
  }
);
  ActionBarMenu menu=actionBar.createMenu();
  masksItem=menu.addItem(gallery_menu_masks,R.drawable.msg_mask);
  pipItem=menu.addItem(gallery_menu_pip,R.drawable.ic_goinline);
  sendItem=menu.addItem(gallery_menu_send,R.drawable.msg_forward);
  menuItem=menu.addItem(0,R.drawable.ic_ab_other);
  menuItem.addSubItem(gallery_menu_openin,R.drawable.msg_openin,LocaleController.getString("OpenInExternalApp",R.string.OpenInExternalApp)).setColors(0xfffafafa,0xfffafafa);
  menuItem.setContentDescription(LocaleController.getString("AccDescrMoreOptions",R.string.AccDescrMoreOptions));
  allMediaItem=menuItem.addSubItem(gallery_menu_showall,R.drawable.msg_media,LocaleController.getString("ShowAllMedia",R.string.ShowAllMedia));
  allMediaItem.setColors(0xfffafafa,0xfffafafa);
  menuItem.addSubItem(gallery_menu_showinchat,R.drawable.msg_message,LocaleController.getString("ShowInChat",R.string.ShowInChat)).setColors(0xfffafafa,0xfffafafa);
  menuItem.addSubItem(gallery_menu_share,R.drawable.msg_shareout,LocaleController.getString("ShareFile",R.string.ShareFile)).setColors(0xfffafafa,0xfffafafa);
  menuItem.addSubItem(gallery_menu_save,R.drawable.msg_gallery,LocaleController.getString("SaveToGallery",R.string.SaveToGallery)).setColors(0xfffafafa,0xfffafafa);
  menuItem.addSubItem(gallery_menu_delete,R.drawable.msg_delete,LocaleController.getString("Delete",R.string.Delete)).setColors(0xfffafafa,0xfffafafa);
  menuItem.addSubItem(gallery_menu_cancel_loading,R.drawable.msg_cancel,LocaleController.getString("StopDownload",R.string.StopDownload)).setColors(0xfffafafa,0xfffafafa);
  menuItem.redrawPopup(0xf9222222);
  sendItem.setContentDescription(LocaleController.getString("Forward",R.string.Forward));
  bottomLayout=new FrameLayout(actvityContext);
  bottomLayout.setBackgroundColor(0x7f000000);
  containerView.addView(bottomLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,48,Gravity.BOTTOM | Gravity.LEFT));
  groupedPhotosListView=new GroupedPhotosListView(actvityContext);
  containerView.addView(groupedPhotosListView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,62,Gravity.BOTTOM | Gravity.LEFT,0,0,0,48));
  groupedPhotosListView.setDelegate(new GroupedPhotosListView.GroupedPhotosListViewDelegate(){
    @Override public int getCurrentIndex(){
      return currentIndex;
    }
    @Override public int getCurrentAccount(){
      return currentAccount;
    }
    @Override public int getAvatarsDialogId(){
      return avatarsDialogId;
    }
    @Override public int getSlideshowMessageId(){
      return slideshowMessageId;
    }
    @Override public ArrayList<ImageLocation> getImagesArrLocations(){
      return imagesArrLocations;
    }
    @Override public ArrayList<MessageObject> getImagesArr(){
      return imagesArr;
    }
    @Override public ArrayList<TLRPC.PageBlock> getPageBlockArr(){
      return null;
    }
    @Override public Object getParentObject(){
      return null;
    }
    @Override public void setCurrentIndex(    int index){
      currentIndex=-1;
      if (currentThumb != null) {
        currentThumb.release();
        currentThumb=null;
      }
      setImageIndex(index,true);
    }
  }
);
  captionTextView=createCaptionTextView();
  switchCaptionTextView=createCaptionTextView();
  for (int a=0; a < 3; a++) {
    photoProgressViews[a]=new PhotoProgressView(containerView.getContext(),containerView);
    photoProgressViews[a].setBackgroundState(0,false);
  }
  miniProgressView=new RadialProgressView(actvityContext){
    @Override public void setAlpha(    float alpha){
      super.setAlpha(alpha);
      if (containerView != null) {
        containerView.invalidate();
      }
    }
    @Override public void invalidate(){
      super.invalidate();
      if (containerView != null) {
        containerView.invalidate();
      }
    }
  }
;
  miniProgressView.setUseSelfAlpha(true);
  miniProgressView.setProgressColor(0xffffffff);
  miniProgressView.setSize(AndroidUtilities.dp(54));
  miniProgressView.setBackgroundResource(R.drawable.circle_big);
  miniProgressView.setVisibility(View.INVISIBLE);
  miniProgressView.setAlpha(0.0f);
  containerView.addView(miniProgressView,LayoutHelper.createFrame(64,64,Gravity.CENTER));
  shareButton=new ImageView(containerView.getContext());
  shareButton.setImageResource(R.drawable.share);
  shareButton.setScaleType(ImageView.ScaleType.CENTER);
  shareButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
  bottomLayout.addView(shareButton,LayoutHelper.createFrame(50,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.RIGHT));
  shareButton.setOnClickListener(v -> onSharePressed());
  shareButton.setContentDescription(LocaleController.getString("ShareFile",R.string.ShareFile));
  nameTextView=new TextView(containerView.getContext());
  nameTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
  nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  nameTextView.setSingleLine(true);
  nameTextView.setMaxLines(1);
  nameTextView.setEllipsize(TextUtils.TruncateAt.END);
  nameTextView.setTextColor(0xffffffff);
  nameTextView.setGravity(Gravity.LEFT);
  bottomLayout.addView(nameTextView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.TOP | Gravity.LEFT,16,5,60,0));
  dateTextView=new TextView(containerView.getContext());
  dateTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,13);
  dateTextView.setSingleLine(true);
  dateTextView.setMaxLines(1);
  dateTextView.setEllipsize(TextUtils.TruncateAt.END);
  dateTextView.setTextColor(0xffffffff);
  dateTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  dateTextView.setGravity(Gravity.LEFT);
  bottomLayout.addView(dateTextView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.TOP | Gravity.LEFT,16,25,50,0));
  createVideoControlsInterface();
  progressView=new RadialProgressView(parentActivity);
  progressView.setProgressColor(0xffffffff);
  progressView.setBackgroundResource(R.drawable.circle_big);
  progressView.setVisibility(View.INVISIBLE);
  containerView.addView(progressView,LayoutHelper.createFrame(54,54,Gravity.CENTER));
  qualityPicker=new PickerBottomLayoutViewer(parentActivity);
  qualityPicker.setBackgroundColor(0x7f000000);
  qualityPicker.updateSelectedCount(0,false);
  qualityPicker.setTranslationY(AndroidUtilities.dp(120));
  qualityPicker.doneButton.setText(LocaleController.getString("Done",R.string.Done).toUpperCase());
  containerView.addView(qualityPicker,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,48,Gravity.BOTTOM | Gravity.LEFT));
  qualityPicker.cancelButton.setOnClickListener(view -> {
    selectedCompression=previousCompression;
    didChangedCompressionLevel(false);
    showQualityView(false);
    requestVideoPreview(2);
  }
);
  qualityPicker.doneButton.setOnClickListener(view -> {
    showQualityView(false);
    requestVideoPreview(2);
  }
);
  videoForwardDrawable=new VideoForwardDrawable();
  videoForwardDrawable.setDelegate(new VideoForwardDrawable.VideoForwardDrawableDelegate(){
    @Override public void onAnimationEnd(){
    }
    @Override public void invalidate(){
      containerView.invalidate();
    }
  }
);
  qualityChooseView=new QualityChooseView(parentActivity);
  qualityChooseView.setTranslationY(AndroidUtilities.dp(120));
  qualityChooseView.setVisibility(View.INVISIBLE);
  qualityChooseView.setBackgroundColor(0x7f000000);
  containerView.addView(qualityChooseView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,70,Gravity.LEFT | Gravity.BOTTOM,0,0,0,48));
  pickerView=new FrameLayout(actvityContext){
    @Override public boolean dispatchTouchEvent(    MotionEvent ev){
      return bottomTouchEnabled && super.dispatchTouchEvent(ev);
    }
    @Override public boolean onInterceptTouchEvent(    MotionEvent ev){
      return bottomTouchEnabled && super.onInterceptTouchEvent(ev);
    }
    @Override public boolean onTouchEvent(    MotionEvent event){
      return bottomTouchEnabled && super.onTouchEvent(event);
    }
  }
;
  pickerView.setBackgroundColor(0x7f000000);
  containerView.addView(pickerView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.BOTTOM | Gravity.LEFT));
  videoTimelineView=new VideoTimelinePlayView(parentActivity);
  videoTimelineView.setDelegate(new VideoTimelinePlayView.VideoTimelineViewDelegate(){
    @Override public void onLeftProgressChanged(    float progress){
      if (videoPlayer == null) {
        return;
      }
      if (videoPlayer.isPlaying()) {
        videoPlayer.pause();
        containerView.invalidate();
      }
      videoPlayer.seekTo((int)(videoDuration * progress));
      videoPlayerSeekbar.setProgress(0);
      videoTimelineView.setProgress(0);
      updateVideoInfo();
    }
    @Override public void onRightProgressChanged(    float progress){
      if (videoPlayer == null) {
        return;
      }
      if (videoPlayer.isPlaying()) {
        videoPlayer.pause();
        containerView.invalidate();
      }
      videoPlayer.seekTo((int)(videoDuration * progress));
      videoPlayerSeekbar.setProgress(0);
      videoTimelineView.setProgress(0);
      updateVideoInfo();
    }
    @Override public void onPlayProgressChanged(    float progress){
      if (videoPlayer == null) {
        return;
      }
      videoPlayer.seekTo((int)(videoDuration * progress));
    }
    @Override public void didStartDragging(){
    }
    @Override public void didStopDragging(){
    }
  }
);
  pickerView.addView(videoTimelineView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,58,Gravity.LEFT | Gravity.TOP,0,8,0,88));
  pickerViewSendButton=new ImageView(parentActivity);
  pickerViewSendButton.setScaleType(ImageView.ScaleType.CENTER);
  Drawable drawable=Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(56),0xff66bffa,0xff66bffa);
  pickerViewSendButton.setBackgroundDrawable(drawable);
  pickerViewSendButton.setColorFilter(new PorterDuffColorFilter(0xffffffff,PorterDuff.Mode.MULTIPLY));
  pickerViewSendButton.setPadding(AndroidUtilities.dp(4),0,0,0);
  pickerViewSendButton.setImageResource(R.drawable.ic_send);
  containerView.addView(pickerViewSendButton,LayoutHelper.createFrame(56,56,Gravity.RIGHT | Gravity.BOTTOM,0,0,14,14));
  pickerViewSendButton.setContentDescription(LocaleController.getString("Send",R.string.Send));
  pickerViewSendButton.setOnClickListener(v -> {
    if (captionEditText.getTag() != null) {
      return;
    }
    if (sendPhotoType == SELECT_TYPE_AVATAR) {
      applyCurrentEditMode();
    }
    if (placeProvider != null && !doneButtonPressed) {
      VideoEditedInfo videoEditedInfo=getCurrentVideoEditedInfo();
      placeProvider.sendButtonPressed(currentIndex,videoEditedInfo);
      doneButtonPressed=true;
      closePhoto(false,false);
    }
  }
);
  LinearLayout itemsLayout=new LinearLayout(parentActivity);
  itemsLayout.setOrientation(LinearLayout.HORIZONTAL);
  pickerView.addView(itemsLayout,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,48,Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,0,0,34,0));
  cropItem=new ImageView(parentActivity);
  cropItem.setScaleType(ImageView.ScaleType.CENTER);
  cropItem.setImageResource(R.drawable.photo_crop);
  cropItem.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
  itemsLayout.addView(cropItem,LayoutHelper.createLinear(70,48));
  cropItem.setOnClickListener(v -> {
    if (captionEditText.getTag() != null) {
      return;
    }
    switchToEditMode(1);
  }
);
  cropItem.setContentDescription(LocaleController.getString("CropImage",R.string.CropImage));
  rotateItem=new ImageView(parentActivity);
  rotateItem.setScaleType(ImageView.ScaleType.CENTER);
  rotateItem.setImageResource(R.drawable.tool_rotate);
  rotateItem.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
  itemsLayout.addView(rotateItem,LayoutHelper.createLinear(70,48));
  rotateItem.setOnClickListener(v -> {
    if (photoCropView == null) {
      return;
    }
    photoCropView.rotate();
  }
);
  rotateItem.setContentDescription(LocaleController.getString("AccDescrRotate",R.string.AccDescrRotate));
  paintItem=new ImageView(parentActivity);
  paintItem.setScaleType(ImageView.ScaleType.CENTER);
  paintItem.setImageResource(R.drawable.photo_paint);
  paintItem.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
  itemsLayout.addView(paintItem,LayoutHelper.createLinear(70,48));
  paintItem.setOnClickListener(v -> {
    if (captionEditText.getTag() != null) {
      return;
    }
    switchToEditMode(3);
  }
);
  paintItem.setContentDescription(LocaleController.getString("AccDescrPhotoEditor",R.string.AccDescrPhotoEditor));
  compressItem=new ImageView(parentActivity);
  compressItem.setTag(1);
  compressItem.setScaleType(ImageView.ScaleType.CENTER);
  compressItem.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  selectedCompression=preferences.getInt("compress_video2",1);
  if (selectedCompression <= 0) {
    compressItem.setImageResource(R.drawable.video_240);
  }
 else   if (selectedCompression == 1) {
    compressItem.setImageResource(R.drawable.video_360);
  }
 else   if (selectedCompression == 2) {
    compressItem.setImageResource(R.drawable.video_480);
  }
 else   if (selectedCompression == 3) {
    compressItem.setImageResource(R.drawable.video_720);
  }
 else   if (selectedCompression == 4) {
    compressItem.setImageResource(R.drawable.video_1080);
  }
  itemsLayout.addView(compressItem,LayoutHelper.createLinear(70,48));
  compressItem.setOnClickListener(v -> {
    if (captionEditText.getTag() != null) {
      return;
    }
    showQualityView(true);
    requestVideoPreview(1);
  }
);
  String[] compressionStrings={"240","360","480","720","1080"};
  compressItem.setContentDescription(LocaleController.getString("AccDescrVideoQuality",R.string.AccDescrVideoQuality) + ", " + compressionStrings[Math.max(0,selectedCompression)]);
  muteItem=new ImageView(parentActivity);
  muteItem.setScaleType(ImageView.ScaleType.CENTER);
  muteItem.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
  itemsLayout.addView(muteItem,LayoutHelper.createLinear(70,48));
  muteItem.setOnClickListener(v -> {
    if (captionEditText.getTag() != null) {
      return;
    }
    muteVideo=!muteVideo;
    updateMuteButton();
    updateVideoInfo();
    if (muteVideo && !checkImageView.isChecked()) {
      checkImageView.callOnClick();
    }
 else {
      Object object=imagesArrLocals.get(currentIndex);
      if (object instanceof MediaController.PhotoEntry) {
        ((MediaController.PhotoEntry)object).editedInfo=getCurrentVideoEditedInfo();
      }
    }
  }
);
  cameraItem=new ImageView(parentActivity);
  cameraItem.setScaleType(ImageView.ScaleType.CENTER);
  cameraItem.setImageResource(R.drawable.photo_add);
  cameraItem.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
  cameraItem.setContentDescription(LocaleController.getString("AccDescrTakeMorePics",R.string.AccDescrTakeMorePics));
  containerView.addView(cameraItem,LayoutHelper.createFrame(48,48,Gravity.RIGHT | Gravity.BOTTOM,0,0,16,0));
  cameraItem.setOnClickListener(v -> {
    if (placeProvider == null || captionEditText.getTag() != null) {
      return;
    }
    placeProvider.needAddMorePhotos();
    closePhoto(true,false);
  }
);
  tuneItem=new ImageView(parentActivity);
  tuneItem.setScaleType(ImageView.ScaleType.CENTER);
  tuneItem.setImageResource(R.drawable.photo_tools);
  tuneItem.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
  itemsLayout.addView(tuneItem,LayoutHelper.createLinear(70,48));
  tuneItem.setOnClickListener(v -> {
    if (captionEditText.getTag() != null) {
      return;
    }
    switchToEditMode(2);
  }
);
  tuneItem.setContentDescription(LocaleController.getString("AccDescrPhotoAdjust",R.string.AccDescrPhotoAdjust));
  timeItem=new ImageView(parentActivity);
  timeItem.setScaleType(ImageView.ScaleType.CENTER);
  timeItem.setImageResource(R.drawable.photo_timer);
  timeItem.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR));
  timeItem.setContentDescription(LocaleController.getString("SetTimer",R.string.SetTimer));
  itemsLayout.addView(timeItem,LayoutHelper.createLinear(70,48));
  timeItem.setOnClickListener(v -> {
    if (parentActivity == null || captionEditText.getTag() != null) {
      return;
    }
    BottomSheet.Builder builder=new BottomSheet.Builder(parentActivity);
    builder.setUseHardwareLayer(false);
    LinearLayout linearLayout=new LinearLayout(parentActivity);
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    builder.setCustomView(linearLayout);
    TextView titleView=new TextView(parentActivity);
    titleView.setLines(1);
    titleView.setSingleLine(true);
    titleView.setText(LocaleController.getString("MessageLifetime",R.string.MessageLifetime));
    titleView.setTextColor(0xffffffff);
    titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
    titleView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    titleView.setPadding(AndroidUtilities.dp(21),AndroidUtilities.dp(8),AndroidUtilities.dp(21),AndroidUtilities.dp(4));
    titleView.setGravity(Gravity.CENTER_VERTICAL);
    linearLayout.addView(titleView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    titleView.setOnTouchListener((v13,event) -> true);
    titleView=new TextView(parentActivity);
    titleView.setText(isCurrentVideo ? LocaleController.getString("MessageLifetimeVideo",R.string.MessageLifetimeVideo) : LocaleController.getString("MessageLifetimePhoto",R.string.MessageLifetimePhoto));
    titleView.setTextColor(0xff808080);
    titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
    titleView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    titleView.setPadding(AndroidUtilities.dp(21),0,AndroidUtilities.dp(21),AndroidUtilities.dp(8));
    titleView.setGravity(Gravity.CENTER_VERTICAL);
    linearLayout.addView(titleView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    titleView.setOnTouchListener((v12,event) -> true);
    final BottomSheet bottomSheet=builder.create();
    final NumberPicker numberPicker=new NumberPicker(parentActivity);
    numberPicker.setMinValue(0);
    numberPicker.setMaxValue(28);
    Object object=imagesArrLocals.get(currentIndex);
    int currentTTL;
    if (object instanceof MediaController.PhotoEntry) {
      currentTTL=((MediaController.PhotoEntry)object).ttl;
    }
 else     if (object instanceof MediaController.SearchImage) {
      currentTTL=((MediaController.SearchImage)object).ttl;
    }
 else {
      currentTTL=0;
    }
    if (currentTTL == 0) {
      SharedPreferences preferences1=MessagesController.getGlobalMainSettings();
      numberPicker.setValue(preferences1.getInt("self_destruct",7));
    }
 else {
      if (currentTTL >= 0 && currentTTL < 21) {
        numberPicker.setValue(currentTTL);
      }
 else {
        numberPicker.setValue(21 + currentTTL / 5 - 5);
      }
    }
    numberPicker.setTextColor(0xffffffff);
    numberPicker.setSelectorColor(0xff4d4d4d);
    numberPicker.setFormatter(value -> {
      if (value == 0) {
        return LocaleController.getString("ShortMessageLifetimeForever",R.string.ShortMessageLifetimeForever);
      }
 else       if (value >= 1 && value < 21) {
        return LocaleController.formatTTLString(value);
      }
 else {
        return LocaleController.formatTTLString((value - 16) * 5);
      }
    }
);
    linearLayout.addView(numberPicker,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    FrameLayout buttonsLayout=new FrameLayout(parentActivity){
      @Override protected void onLayout(      boolean changed,      int left,      int top,      int right,      int bottom){
        int count=getChildCount();
        View positiveButton=null;
        int width=right - left;
        for (int a=0; a < count; a++) {
          View child=getChildAt(a);
          if ((Integer)child.getTag() == Dialog.BUTTON_POSITIVE) {
            positiveButton=child;
            child.layout(width - getPaddingRight() - child.getMeasuredWidth(),getPaddingTop(),width - getPaddingRight() + child.getMeasuredWidth(),getPaddingTop() + child.getMeasuredHeight());
          }
 else           if ((Integer)child.getTag() == Dialog.BUTTON_NEGATIVE) {
            int x=width - getPaddingRight() - child.getMeasuredWidth();
            if (positiveButton != null) {
              x-=positiveButton.getMeasuredWidth() + AndroidUtilities.dp(8);
            }
            child.layout(x,getPaddingTop(),x + child.getMeasuredWidth(),getPaddingTop() + child.getMeasuredHeight());
          }
 else {
            child.layout(getPaddingLeft(),getPaddingTop(),getPaddingLeft() + child.getMeasuredWidth(),getPaddingTop() + child.getMeasuredHeight());
          }
        }
      }
    }
;
    buttonsLayout.setPadding(AndroidUtilities.dp(8),AndroidUtilities.dp(8),AndroidUtilities.dp(8),AndroidUtilities.dp(8));
    linearLayout.addView(buttonsLayout,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,52));
    TextView textView=new TextView(parentActivity);
    textView.setMinWidth(AndroidUtilities.dp(64));
    textView.setTag(Dialog.BUTTON_POSITIVE);
    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
    textView.setTextColor(0xff49bcf2);
    textView.setGravity(Gravity.CENTER);
    textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
    textView.setText(LocaleController.getString("Done",R.string.Done).toUpperCase());
    textView.setBackgroundDrawable(Theme.getRoundRectSelectorDrawable(0xff49bcf2));
    textView.setPadding(AndroidUtilities.dp(10),0,AndroidUtilities.dp(10),0);
    buttonsLayout.addView(textView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,36,Gravity.TOP | Gravity.RIGHT));
    textView.setOnClickListener(v1 -> {
      int value=numberPicker.getValue();
      SharedPreferences preferences1=MessagesController.getGlobalMainSettings();
      SharedPreferences.Editor editor=preferences1.edit();
      editor.putInt("self_destruct",value);
      editor.commit();
      bottomSheet.dismiss();
      int seconds;
      if (value >= 0 && value < 21) {
        seconds=value;
      }
 else {
        seconds=(value - 16) * 5;
      }
      Object object1=imagesArrLocals.get(currentIndex);
      if (object1 instanceof MediaController.PhotoEntry) {
        ((MediaController.PhotoEntry)object1).ttl=seconds;
      }
 else       if (object1 instanceof MediaController.SearchImage) {
        ((MediaController.SearchImage)object1).ttl=seconds;
      }
      timeItem.setColorFilter(seconds != 0 ? new PorterDuffColorFilter(0xff3dadee,PorterDuff.Mode.MULTIPLY) : null);
      if (!checkImageView.isChecked()) {
        checkImageView.callOnClick();
      }
    }
);
    textView=new TextView(parentActivity);
    textView.setMinWidth(AndroidUtilities.dp(64));
    textView.setTag(Dialog.BUTTON_NEGATIVE);
    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
    textView.setTextColor(0xff49bcf2);
    textView.setGravity(Gravity.CENTER);
    textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
    textView.setText(LocaleController.getString("Cancel",R.string.Cancel).toUpperCase());
    textView.setBackgroundDrawable(Theme.getRoundRectSelectorDrawable(0xff49bcf2));
    textView.setPadding(AndroidUtilities.dp(10),0,AndroidUtilities.dp(10),0);
    buttonsLayout.addView(textView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,36,Gravity.TOP | Gravity.RIGHT));
    textView.setOnClickListener(v14 -> bottomSheet.dismiss());
    bottomSheet.show();
    bottomSheet.setBackgroundColor(0xff000000);
  }
);
  editorDoneLayout=new PickerBottomLayoutViewer(actvityContext);
  editorDoneLayout.setBackgroundColor(0x7f000000);
  editorDoneLayout.updateSelectedCount(0,false);
  editorDoneLayout.setVisibility(View.GONE);
  containerView.addView(editorDoneLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,48,Gravity.LEFT | Gravity.BOTTOM));
  editorDoneLayout.cancelButton.setOnClickListener(view -> switchToEditMode(0));
  editorDoneLayout.doneButton.setOnClickListener(view -> {
    if (currentEditMode == 1 && !photoCropView.isReady()) {
      return;
    }
    applyCurrentEditMode();
    switchToEditMode(0);
  }
);
  resetButton=new TextView(actvityContext);
  resetButton.setVisibility(View.GONE);
  resetButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
  resetButton.setTextColor(0xffffffff);
  resetButton.setGravity(Gravity.CENTER);
  resetButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.ACTION_BAR_PICKER_SELECTOR_COLOR,0));
  resetButton.setPadding(AndroidUtilities.dp(20),0,AndroidUtilities.dp(20),0);
  resetButton.setText(LocaleController.getString("Reset",R.string.CropReset).toUpperCase());
  resetButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  editorDoneLayout.addView(resetButton,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.CENTER));
  resetButton.setOnClickListener(v -> photoCropView.reset());
  gestureDetector=new GestureDetector(containerView.getContext(),this);
  setDoubleTapEnabled(true);
  ImageReceiver.ImageReceiverDelegate imageReceiverDelegate=(imageReceiver,set,thumb) -> {
    if (imageReceiver == centerImage && set && !thumb && (currentEditMode == 1 || sendPhotoType == SELECT_TYPE_AVATAR) && photoCropView != null) {
      Bitmap bitmap=imageReceiver.getBitmap();
      if (bitmap != null) {
        photoCropView.setBitmap(bitmap,imageReceiver.getOrientation(),sendPhotoType != SELECT_TYPE_AVATAR,true);
      }
    }
    if (imageReceiver == centerImage && set && placeProvider != null && placeProvider.scaleToFill() && !ignoreDidSetImage) {
      if (!wasLayout) {
        dontResetZoomOnFirstLayout=true;
      }
 else {
        setScaleToFill();
      }
    }
  }
;
  centerImage.setParentView(containerView);
  centerImage.setCrossfadeAlpha((byte)2);
  centerImage.setInvalidateAll(true);
  centerImage.setDelegate(imageReceiverDelegate);
  leftImage.setParentView(containerView);
  leftImage.setCrossfadeAlpha((byte)2);
  leftImage.setInvalidateAll(true);
  leftImage.setDelegate(imageReceiverDelegate);
  rightImage.setParentView(containerView);
  rightImage.setCrossfadeAlpha((byte)2);
  rightImage.setInvalidateAll(true);
  rightImage.setDelegate(imageReceiverDelegate);
  WindowManager manager=(WindowManager)ApplicationLoader.applicationContext.getSystemService(Activity.WINDOW_SERVICE);
  int rotation=manager.getDefaultDisplay().getRotation();
  checkImageView=new CheckBox(containerView.getContext(),R.drawable.selectphoto_large){
    @Override public boolean onTouchEvent(    MotionEvent event){
      return bottomTouchEnabled && super.onTouchEvent(event);
    }
  }
;
  checkImageView.setDrawBackground(true);
  checkImageView.setHasBorder(true);
  checkImageView.setSize(40);
  checkImageView.setCheckOffset(AndroidUtilities.dp(1));
  checkImageView.setColor(0xff66bffa,0xffffffff);
  checkImageView.setVisibility(View.GONE);
  containerView.addView(checkImageView,LayoutHelper.createFrame(40,40,Gravity.RIGHT | Gravity.TOP,0,rotation == Surface.ROTATION_270 || rotation == Surface.ROTATION_90 ? 58 : 68,10,0));
  if (Build.VERSION.SDK_INT >= 21) {
    ((FrameLayout.LayoutParams)checkImageView.getLayoutParams()).topMargin+=AndroidUtilities.statusBarHeight;
  }
  checkImageView.setOnClickListener(v -> {
    if (captionEditText.getTag() != null) {
      return;
    }
    setPhotoChecked();
  }
);
  photosCounterView=new CounterView(parentActivity);
  containerView.addView(photosCounterView,LayoutHelper.createFrame(40,40,Gravity.RIGHT | Gravity.TOP,0,rotation == Surface.ROTATION_270 || rotation == Surface.ROTATION_90 ? 58 : 68,66,0));
  if (Build.VERSION.SDK_INT >= 21) {
    ((FrameLayout.LayoutParams)photosCounterView.getLayoutParams()).topMargin+=AndroidUtilities.statusBarHeight;
  }
  photosCounterView.setOnClickListener(v -> {
    if (captionEditText.getTag() != null || placeProvider == null || placeProvider.getSelectedPhotosOrder() == null || placeProvider.getSelectedPhotosOrder().isEmpty()) {
      return;
    }
    togglePhotosListView(!isPhotosListViewVisible,true);
  }
);
  selectedPhotosListView=new RecyclerListView(parentActivity);
  selectedPhotosListView.setVisibility(View.GONE);
  selectedPhotosListView.setAlpha(0.0f);
  selectedPhotosListView.setTranslationY(-AndroidUtilities.dp(10));
  selectedPhotosListView.addItemDecoration(new RecyclerView.ItemDecoration(){
    @Override public void getItemOffsets(    Rect outRect,    View view,    RecyclerView parent,    RecyclerView.State state){
      int position=parent.getChildAdapterPosition(view);
      if (view instanceof PhotoPickerPhotoCell && position == 0) {
        outRect.left=AndroidUtilities.dp(3);
      }
 else {
        outRect.left=0;
      }
      outRect.right=AndroidUtilities.dp(3);
    }
  }
);
  ((DefaultItemAnimator)selectedPhotosListView.getItemAnimator()).setDelayAnimations(false);
  selectedPhotosListView.setBackgroundColor(0x7f000000);
  selectedPhotosListView.setPadding(0,AndroidUtilities.dp(3),0,AndroidUtilities.dp(3));
  selectedPhotosListView.setLayoutManager(new LinearLayoutManager(parentActivity,LinearLayoutManager.HORIZONTAL,false){
    @Override public void smoothScrollToPosition(    RecyclerView recyclerView,    RecyclerView.State state,    int position){
      LinearSmoothScrollerEnd linearSmoothScroller=new LinearSmoothScrollerEnd(recyclerView.getContext());
      linearSmoothScroller.setTargetPosition(position);
      startSmoothScroll(linearSmoothScroller);
    }
  }
);
  selectedPhotosListView.setAdapter(selectedPhotosAdapter=new ListAdapter(parentActivity));
  containerView.addView(selectedPhotosListView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,88,Gravity.LEFT | Gravity.TOP));
  selectedPhotosListView.setOnItemClickListener((view,position) -> {
    if (position == 0 && placeProvider.allowGroupPhotos()) {
      boolean enabled=SharedConfig.groupPhotosEnabled;
      SharedConfig.toggleGroupPhotosEnabled();
      placeProvider.toggleGroupPhotosEnabled();
      ImageView imageView=(ImageView)view;
      imageView.setColorFilter(!enabled ? new PorterDuffColorFilter(0xff66bffa,PorterDuff.Mode.MULTIPLY) : null);
      imageView.setContentDescription(SharedConfig.groupPhotosEnabled ? LocaleController.getString("GroupPhotosHelp",R.string.GroupPhotosHelp) : LocaleController.getString("SinglePhotosHelp",R.string.SinglePhotosHelp));
      showHint(false,!enabled);
    }
 else {
      ignoreDidSetImage=true;
      int idx=imagesArrLocals.indexOf(view.getTag());
      if (idx >= 0) {
        currentIndex=-1;
        setImageIndex(idx,true);
      }
      ignoreDidSetImage=false;
    }
  }
);
  captionEditText=new PhotoViewerCaptionEnterView(actvityContext,containerView,windowView){
    @Override public boolean dispatchTouchEvent(    MotionEvent ev){
      try {
        return !bottomTouchEnabled && super.dispatchTouchEvent(ev);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      return false;
    }
    @Override public boolean onInterceptTouchEvent(    MotionEvent ev){
      try {
        return !bottomTouchEnabled && super.onInterceptTouchEvent(ev);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      return false;
    }
    @Override public boolean onTouchEvent(    MotionEvent event){
      return !bottomTouchEnabled && super.onTouchEvent(event);
    }
  }
;
  captionEditText.setDelegate(new PhotoViewerCaptionEnterView.PhotoViewerCaptionEnterViewDelegate(){
    @Override public void onCaptionEnter(){
      closeCaptionEnter(true);
    }
    @Override public void onTextChanged(    CharSequence text){
      if (mentionsAdapter != null && captionEditText != null && parentChatActivity != null && text != null) {
        mentionsAdapter.searchUsernameOrHashtag(text.toString(),captionEditText.getCursorPosition(),parentChatActivity.messages,false);
      }
    }
    @Override public void onWindowSizeChanged(    int size){
      int height=AndroidUtilities.dp(36 * Math.min(3,mentionsAdapter.getItemCount()) + (mentionsAdapter.getItemCount() > 3 ? 18 : 0));
      if (size - ActionBar.getCurrentActionBarHeight() * 2 < height) {
        allowMentions=false;
        if (mentionListView != null && mentionListView.getVisibility() == View.VISIBLE) {
          mentionListView.setVisibility(View.INVISIBLE);
        }
      }
 else {
        allowMentions=true;
        if (mentionListView != null && mentionListView.getVisibility() == View.INVISIBLE) {
          mentionListView.setVisibility(View.VISIBLE);
        }
      }
    }
  }
);
  if (Build.VERSION.SDK_INT >= 19)   captionEditText.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);
  containerView.addView(captionEditText,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.BOTTOM | Gravity.LEFT));
  mentionListView=new RecyclerListView(actvityContext){
    @Override public boolean dispatchTouchEvent(    MotionEvent ev){
      return !bottomTouchEnabled && super.dispatchTouchEvent(ev);
    }
    @Override public boolean onInterceptTouchEvent(    MotionEvent ev){
      return !bottomTouchEnabled && super.onInterceptTouchEvent(ev);
    }
    @Override public boolean onTouchEvent(    MotionEvent event){
      return !bottomTouchEnabled && super.onTouchEvent(event);
    }
  }
;
  mentionListView.setTag(5);
  mentionLayoutManager=new LinearLayoutManager(actvityContext){
    @Override public boolean supportsPredictiveItemAnimations(){
      return false;
    }
  }
;
  mentionLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
  mentionListView.setLayoutManager(mentionLayoutManager);
  mentionListView.setBackgroundColor(0x7f000000);
  mentionListView.setVisibility(View.GONE);
  mentionListView.setClipToPadding(true);
  mentionListView.setOverScrollMode(RecyclerListView.OVER_SCROLL_NEVER);
  containerView.addView(mentionListView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,110,Gravity.LEFT | Gravity.BOTTOM));
  mentionListView.setAdapter(mentionsAdapter=new MentionsAdapter(actvityContext,true,0,new MentionsAdapter.MentionsAdapterDelegate(){
    @Override public void needChangePanelVisibility(    boolean show){
      if (show) {
        FrameLayout.LayoutParams layoutParams3=(FrameLayout.LayoutParams)mentionListView.getLayoutParams();
        int height=36 * Math.min(3,mentionsAdapter.getItemCount()) + (mentionsAdapter.getItemCount() > 3 ? 18 : 0);
        layoutParams3.height=AndroidUtilities.dp(height);
        layoutParams3.topMargin=-AndroidUtilities.dp(height);
        mentionListView.setLayoutParams(layoutParams3);
        if (mentionListAnimation != null) {
          mentionListAnimation.cancel();
          mentionListAnimation=null;
        }
        if (mentionListView.getVisibility() == View.VISIBLE) {
          mentionListView.setAlpha(1.0f);
          return;
        }
 else {
          mentionLayoutManager.scrollToPositionWithOffset(0,10000);
        }
        if (allowMentions) {
          mentionListView.setVisibility(View.VISIBLE);
          mentionListAnimation=new AnimatorSet();
          mentionListAnimation.playTogether(ObjectAnimator.ofFloat(mentionListView,View.ALPHA,0.0f,1.0f));
          mentionListAnimation.addListener(new AnimatorListenerAdapter(){
            @Override public void onAnimationEnd(            Animator animation){
              if (mentionListAnimation != null && mentionListAnimation.equals(animation)) {
                mentionListAnimation=null;
              }
            }
          }
);
          mentionListAnimation.setDuration(200);
          mentionListAnimation.start();
        }
 else {
          mentionListView.setAlpha(1.0f);
          mentionListView.setVisibility(View.INVISIBLE);
        }
      }
 else {
        if (mentionListAnimation != null) {
          mentionListAnimation.cancel();
          mentionListAnimation=null;
        }
        if (mentionListView.getVisibility() == View.GONE) {
          return;
        }
        if (allowMentions) {
          mentionListAnimation=new AnimatorSet();
          mentionListAnimation.playTogether(ObjectAnimator.ofFloat(mentionListView,View.ALPHA,0.0f));
          mentionListAnimation.addListener(new AnimatorListenerAdapter(){
            @Override public void onAnimationEnd(            Animator animation){
              if (mentionListAnimation != null && mentionListAnimation.equals(animation)) {
                mentionListView.setVisibility(View.GONE);
                mentionListAnimation=null;
              }
            }
          }
);
          mentionListAnimation.setDuration(200);
          mentionListAnimation.start();
        }
 else {
          mentionListView.setVisibility(View.GONE);
        }
      }
    }
    @Override public void onContextSearch(    boolean searching){
    }
    @Override public void onContextClick(    TLRPC.BotInlineResult result){
    }
  }
));
  mentionListView.setOnItemClickListener((view,position) -> {
    Object object=mentionsAdapter.getItem(position);
    int start=mentionsAdapter.getResultStartPosition();
    int len=mentionsAdapter.getResultLength();
    if (object instanceof TLRPC.User) {
      TLRPC.User user=(TLRPC.User)object;
      if (user.username != null) {
        captionEditText.replaceWithText(start,len,"@" + user.username + " ",false);
      }
 else {
        String name=UserObject.getFirstName(user);
        Spannable spannable=new SpannableString(name + " ");
        spannable.setSpan(new URLSpanUserMentionPhotoViewer("" + user.id,true),0,spannable.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        captionEditText.replaceWithText(start,len,spannable,false);
      }
    }
 else     if (object instanceof String) {
      captionEditText.replaceWithText(start,len,object + " ",false);
    }
 else     if (object instanceof DataQuery.KeywordResult) {
      String code=((DataQuery.KeywordResult)object).emoji;
      captionEditText.addEmojiToRecent(code);
      captionEditText.replaceWithText(start,len,code,true);
    }
  }
);
  mentionListView.setOnItemLongClickListener((view,position) -> {
    Object object=mentionsAdapter.getItem(position);
    if (object instanceof String) {
      AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
      builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
      builder.setMessage(LocaleController.getString("ClearSearch",R.string.ClearSearch));
      builder.setPositiveButton(LocaleController.getString("ClearButton",R.string.ClearButton).toUpperCase(),(dialogInterface,i) -> mentionsAdapter.clearRecentHashtags());
      builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
      showAlertDialog(builder);
      return true;
    }
    return false;
  }
);
  AccessibilityManager am=(AccessibilityManager)actvityContext.getSystemService(Context.ACCESSIBILITY_SERVICE);
  if (am.isEnabled()) {
    playButtonAccessibilityOverlay=new View(actvityContext);
    playButtonAccessibilityOverlay.setContentDescription(LocaleController.getString("AccActionPlay",R.string.AccActionPlay));
    playButtonAccessibilityOverlay.setFocusable(true);
    containerView.addView(playButtonAccessibilityOverlay,LayoutHelper.createFrame(64,64,Gravity.CENTER));
  }
}
