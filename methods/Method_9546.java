private ViewGroup getViewForMessage(int num,boolean applyOffset){
  if (popupMessages.size() == 1 && (num < 0 || num >= popupMessages.size())) {
    return null;
  }
  if (num == -1) {
    num=popupMessages.size() - 1;
  }
 else   if (num == popupMessages.size()) {
    num=0;
  }
  ViewGroup view;
  MessageObject messageObject=popupMessages.get(num);
  if ((messageObject.type == 1 || messageObject.type == 4) && !messageObject.isSecretMedia()) {
    if (imageViews.size() > 0) {
      view=imageViews.get(0);
      imageViews.remove(0);
    }
 else {
      view=new FrameLayout(this);
      FrameLayout frameLayout=new FrameLayout(this);
      frameLayout.setPadding(AndroidUtilities.dp(10),AndroidUtilities.dp(10),AndroidUtilities.dp(10),AndroidUtilities.dp(10));
      frameLayout.setBackgroundDrawable(Theme.getSelectorDrawable(false));
      view.addView(frameLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
      BackupImageView backupImageView=new BackupImageView(this);
      backupImageView.setTag(311);
      frameLayout.addView(backupImageView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
      TextView textView=new TextView(this);
      textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
      textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
      textView.setGravity(Gravity.CENTER);
      textView.setTag(312);
      frameLayout.addView(textView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.CENTER));
      view.setTag(2);
      view.setOnClickListener(v -> openCurrentMessage());
    }
    TextView messageText=view.findViewWithTag(312);
    BackupImageView imageView=view.findViewWithTag(311);
    imageView.setAspectFit(true);
    if (messageObject.type == 1) {
      TLRPC.PhotoSize currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,AndroidUtilities.getPhotoSize());
      TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,100);
      boolean photoSet=false;
      if (currentPhotoObject != null) {
        boolean photoExist=true;
        if (messageObject.type == 1) {
          File cacheFile=FileLoader.getPathToMessage(messageObject.messageOwner);
          if (!cacheFile.exists()) {
            photoExist=false;
          }
        }
        if (!messageObject.needDrawBluredPreview()) {
          if (photoExist || DownloadController.getInstance(messageObject.currentAccount).canDownloadMedia(messageObject)) {
            imageView.setImage(ImageLocation.getForObject(currentPhotoObject,messageObject.photoThumbsObject),"100_100",ImageLocation.getForObject(thumb,messageObject.photoThumbsObject),"100_100_b",currentPhotoObject.size,messageObject);
            photoSet=true;
          }
 else {
            if (thumb != null) {
              imageView.setImage(ImageLocation.getForObject(thumb,messageObject.photoThumbsObject),"100_100_b",null,null,messageObject);
              photoSet=true;
            }
          }
        }
      }
      if (!photoSet) {
        imageView.setVisibility(View.GONE);
        messageText.setVisibility(View.VISIBLE);
        messageText.setTextSize(TypedValue.COMPLEX_UNIT_SP,SharedConfig.fontSize);
        messageText.setText(messageObject.messageText);
      }
 else {
        imageView.setVisibility(View.VISIBLE);
        messageText.setVisibility(View.GONE);
      }
    }
 else     if (messageObject.type == 4) {
      messageText.setVisibility(View.GONE);
      messageText.setText(messageObject.messageText);
      imageView.setVisibility(View.VISIBLE);
      TLRPC.GeoPoint geoPoint=messageObject.messageOwner.media.geo;
      double lat=geoPoint.lat;
      double lon=geoPoint._long;
      if (MessagesController.getInstance(messageObject.currentAccount).mapProvider == 2) {
        imageView.setImage(ImageLocation.getForWebFile(WebFile.createWithGeoPoint(geoPoint,100,100,15,Math.min(2,(int)Math.ceil(AndroidUtilities.density)))),null,null,null,messageObject);
      }
 else {
        String currentUrl=AndroidUtilities.formapMapUrl(messageObject.currentAccount,lat,lon,100,100,true,15);
        imageView.setImage(currentUrl,null,null);
      }
    }
  }
 else   if (messageObject.type == 2) {
    PopupAudioView cell;
    if (audioViews.size() > 0) {
      view=audioViews.get(0);
      audioViews.remove(0);
      cell=view.findViewWithTag(300);
    }
 else {
      view=new FrameLayout(this);
      FrameLayout frameLayout=new FrameLayout(this);
      frameLayout.setPadding(AndroidUtilities.dp(10),AndroidUtilities.dp(10),AndroidUtilities.dp(10),AndroidUtilities.dp(10));
      frameLayout.setBackgroundDrawable(Theme.getSelectorDrawable(false));
      view.addView(frameLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
      FrameLayout frameLayout1=new FrameLayout(this);
      frameLayout.addView(frameLayout1,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.CENTER,20,0,20,0));
      cell=new PopupAudioView(this);
      cell.setTag(300);
      frameLayout1.addView(cell);
      view.setTag(3);
      view.setOnClickListener(v -> openCurrentMessage());
    }
    cell.setMessageObject(messageObject);
    if (DownloadController.getInstance(messageObject.currentAccount).canDownloadMedia(messageObject)) {
      cell.downloadAudioIfNeed();
    }
  }
 else {
    if (textViews.size() > 0) {
      view=textViews.get(0);
      textViews.remove(0);
    }
 else {
      view=new FrameLayout(this);
      ScrollView scrollView=new ScrollView(this);
      scrollView.setFillViewport(true);
      view.addView(scrollView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
      LinearLayout linearLayout=new LinearLayout(this);
      linearLayout.setOrientation(LinearLayout.HORIZONTAL);
      linearLayout.setBackgroundDrawable(Theme.getSelectorDrawable(false));
      scrollView.addView(linearLayout,LayoutHelper.createScroll(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.CENTER_HORIZONTAL));
      linearLayout.setPadding(AndroidUtilities.dp(10),AndroidUtilities.dp(10),AndroidUtilities.dp(10),AndroidUtilities.dp(10));
      linearLayout.setOnClickListener(v -> openCurrentMessage());
      TextView textView=new TextView(this);
      textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
      textView.setTag(301);
      textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
      textView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
      textView.setGravity(Gravity.CENTER);
      linearLayout.addView(textView,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.CENTER));
      view.setTag(1);
    }
    TextView messageText=view.findViewWithTag(301);
    messageText.setTextSize(TypedValue.COMPLEX_UNIT_SP,SharedConfig.fontSize);
    messageText.setText(messageObject.messageText);
  }
  if (view.getParent() == null) {
    messageContainer.addView(view);
  }
  view.setVisibility(View.VISIBLE);
  if (applyOffset) {
    int widht=AndroidUtilities.displaySize.x - AndroidUtilities.dp(24);
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)view.getLayoutParams();
    layoutParams.gravity=Gravity.TOP | Gravity.LEFT;
    layoutParams.height=ViewGroup.LayoutParams.MATCH_PARENT;
    layoutParams.width=widht;
    if (num == currentMessageNum) {
      view.setTranslationX(0);
    }
 else     if (num == currentMessageNum - 1) {
      view.setTranslationX(-widht);
    }
 else     if (num == currentMessageNum + 1) {
      view.setTranslationX(widht);
    }
    view.setLayoutParams(layoutParams);
    view.invalidate();
  }
  return view;
}
