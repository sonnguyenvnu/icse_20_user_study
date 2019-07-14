private void switchToEditMode(final int mode){
  if (currentEditMode == mode || centerImage.getBitmap() == null || changeModeAnimation != null || imageMoveAnimation != null || photoProgressViews[0].backgroundState != -1 || captionEditText.getTag() != null) {
    return;
  }
  if (mode == 0) {
    Bitmap bitmap=centerImage.getBitmap();
    if (bitmap != null) {
      int bitmapWidth=centerImage.getBitmapWidth();
      int bitmapHeight=centerImage.getBitmapHeight();
      float scaleX=(float)getContainerViewWidth() / (float)bitmapWidth;
      float scaleY=(float)getContainerViewHeight() / (float)bitmapHeight;
      float newScaleX=(float)getContainerViewWidth(0) / (float)bitmapWidth;
      float newScaleY=(float)getContainerViewHeight(0) / (float)bitmapHeight;
      float scale=scaleX > scaleY ? scaleY : scaleX;
      float newScale=newScaleX > newScaleY ? newScaleY : newScaleX;
      if (sendPhotoType == SELECT_TYPE_AVATAR) {
        setCropTranslations(true);
      }
 else {
        animateToScale=newScale / scale;
        animateToX=0;
        translationX=getLeftInset() / 2 - getRightInset() / 2;
        if (currentEditMode == 1) {
          animateToY=AndroidUtilities.dp(24 + 34);
        }
 else         if (currentEditMode == 2) {
          animateToY=AndroidUtilities.dp(92);
        }
 else         if (currentEditMode == 3) {
          animateToY=AndroidUtilities.dp(44);
        }
        if (Build.VERSION.SDK_INT >= 21) {
          animateToY-=AndroidUtilities.statusBarHeight / 2;
        }
        animationStartTime=System.currentTimeMillis();
        zoomAnimation=true;
      }
    }
    padImageForHorizontalInsets=false;
    imageMoveAnimation=new AnimatorSet();
    ArrayList<Animator> animators=new ArrayList<>(4);
    if (currentEditMode == 1) {
      animators.add(ObjectAnimator.ofFloat(editorDoneLayout,View.TRANSLATION_Y,AndroidUtilities.dp(48)));
      animators.add(ObjectAnimator.ofFloat(PhotoViewer.this,AnimationProperties.PHOTO_VIEWER_ANIMATION_VALUE,0,1));
      animators.add(ObjectAnimator.ofFloat(photoCropView,View.ALPHA,0));
    }
 else     if (currentEditMode == 2) {
      photoFilterView.shutdown();
      animators.add(ObjectAnimator.ofFloat(photoFilterView.getToolsView(),View.TRANSLATION_Y,AndroidUtilities.dp(186)));
      animators.add(ObjectAnimator.ofFloat(PhotoViewer.this,AnimationProperties.PHOTO_VIEWER_ANIMATION_VALUE,0,1));
    }
 else     if (currentEditMode == 3) {
      photoPaintView.shutdown();
      animators.add(ObjectAnimator.ofFloat(photoPaintView.getToolsView(),View.TRANSLATION_Y,AndroidUtilities.dp(126)));
      animators.add(ObjectAnimator.ofFloat(photoPaintView.getColorPicker(),View.TRANSLATION_Y,AndroidUtilities.dp(126)));
      animators.add(ObjectAnimator.ofFloat(PhotoViewer.this,AnimationProperties.PHOTO_VIEWER_ANIMATION_VALUE,0,1));
    }
    imageMoveAnimation.playTogether(animators);
    imageMoveAnimation.setDuration(200);
    imageMoveAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (currentEditMode == 1) {
          editorDoneLayout.setVisibility(View.GONE);
          photoCropView.setVisibility(View.GONE);
        }
 else         if (currentEditMode == 2) {
          containerView.removeView(photoFilterView);
          photoFilterView=null;
        }
 else         if (currentEditMode == 3) {
          containerView.removeView(photoPaintView);
          photoPaintView=null;
        }
        imageMoveAnimation=null;
        currentEditMode=mode;
        applying=false;
        if (sendPhotoType != SELECT_TYPE_AVATAR) {
          animateToScale=1;
          animateToX=0;
          animateToY=0;
          scale=1;
        }
        updateMinMax(scale);
        containerView.invalidate();
        AnimatorSet animatorSet=new AnimatorSet();
        ArrayList<Animator> arrayList=new ArrayList<>();
        arrayList.add(ObjectAnimator.ofFloat(pickerView,View.TRANSLATION_Y,0));
        arrayList.add(ObjectAnimator.ofFloat(pickerViewSendButton,View.TRANSLATION_Y,0));
        if (sendPhotoType != SELECT_TYPE_AVATAR) {
          arrayList.add(ObjectAnimator.ofFloat(actionBar,View.TRANSLATION_Y,0));
        }
        if (needCaptionLayout) {
          arrayList.add(ObjectAnimator.ofFloat(captionTextView,View.TRANSLATION_Y,0));
        }
        if (sendPhotoType == 0 || sendPhotoType == 4) {
          arrayList.add(ObjectAnimator.ofFloat(checkImageView,View.ALPHA,1));
          arrayList.add(ObjectAnimator.ofFloat(photosCounterView,View.ALPHA,1));
        }
 else         if (sendPhotoType == SELECT_TYPE_AVATAR) {
          arrayList.add(ObjectAnimator.ofFloat(photoCropView,View.ALPHA,1));
        }
        if (cameraItem.getTag() != null) {
          cameraItem.setVisibility(View.VISIBLE);
          arrayList.add(ObjectAnimator.ofFloat(cameraItem,View.ALPHA,1));
        }
        animatorSet.playTogether(arrayList);
        animatorSet.setDuration(200);
        animatorSet.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationStart(          Animator animation){
            pickerView.setVisibility(View.VISIBLE);
            pickerViewSendButton.setVisibility(View.VISIBLE);
            actionBar.setVisibility(View.VISIBLE);
            if (needCaptionLayout) {
              captionTextView.setVisibility(captionTextView.getTag() != null ? View.VISIBLE : View.INVISIBLE);
            }
            if (sendPhotoType == 0 || sendPhotoType == 4 || (sendPhotoType == 2 || sendPhotoType == 5) && imagesArrLocals.size() > 1) {
              checkImageView.setVisibility(View.VISIBLE);
              photosCounterView.setVisibility(View.VISIBLE);
            }
 else             if (sendPhotoType == SELECT_TYPE_AVATAR) {
              setCropTranslations(false);
            }
          }
        }
);
        animatorSet.start();
      }
    }
);
    imageMoveAnimation.start();
  }
 else   if (mode == 1) {
    createCropView();
    photoCropView.onAppear();
    editorDoneLayout.doneButton.setText(LocaleController.getString("Crop",R.string.Crop));
    editorDoneLayout.doneButton.setTextColor(0xff51bdf3);
    changeModeAnimation=new AnimatorSet();
    ArrayList<Animator> arrayList=new ArrayList<>();
    arrayList.add(ObjectAnimator.ofFloat(pickerView,View.TRANSLATION_Y,0,AndroidUtilities.dp(96)));
    arrayList.add(ObjectAnimator.ofFloat(pickerViewSendButton,View.TRANSLATION_Y,0,AndroidUtilities.dp(96)));
    arrayList.add(ObjectAnimator.ofFloat(actionBar,View.TRANSLATION_Y,0,-actionBar.getHeight()));
    if (needCaptionLayout) {
      arrayList.add(ObjectAnimator.ofFloat(captionTextView,View.TRANSLATION_Y,0,AndroidUtilities.dp(96)));
    }
    if (sendPhotoType == 0 || sendPhotoType == 4) {
      arrayList.add(ObjectAnimator.ofFloat(checkImageView,View.ALPHA,1,0));
      arrayList.add(ObjectAnimator.ofFloat(photosCounterView,View.ALPHA,1,0));
    }
    if (selectedPhotosListView.getVisibility() == View.VISIBLE) {
      arrayList.add(ObjectAnimator.ofFloat(selectedPhotosListView,View.ALPHA,1,0));
    }
    if (cameraItem.getTag() != null) {
      arrayList.add(ObjectAnimator.ofFloat(cameraItem,View.ALPHA,1,0));
    }
    changeModeAnimation.playTogether(arrayList);
    changeModeAnimation.setDuration(200);
    changeModeAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        changeModeAnimation=null;
        pickerView.setVisibility(View.GONE);
        pickerViewSendButton.setVisibility(View.GONE);
        cameraItem.setVisibility(View.GONE);
        selectedPhotosListView.setVisibility(View.GONE);
        selectedPhotosListView.setAlpha(0.0f);
        selectedPhotosListView.setTranslationY(-AndroidUtilities.dp(10));
        photosCounterView.setRotationX(0.0f);
        selectedPhotosListView.setEnabled(false);
        isPhotosListViewVisible=false;
        if (needCaptionLayout) {
          captionTextView.setVisibility(View.INVISIBLE);
        }
        if (sendPhotoType == 0 || sendPhotoType == 4 || (sendPhotoType == 2 || sendPhotoType == 5) && imagesArrLocals.size() > 1) {
          checkImageView.setVisibility(View.GONE);
          photosCounterView.setVisibility(View.GONE);
        }
        final Bitmap bitmap=centerImage.getBitmap();
        if (bitmap != null) {
          photoCropView.setBitmap(bitmap,centerImage.getOrientation(),sendPhotoType != SELECT_TYPE_AVATAR,false);
          photoCropView.onDisappear();
          int bitmapWidth=centerImage.getBitmapWidth();
          int bitmapHeight=centerImage.getBitmapHeight();
          float scaleX=(float)getContainerViewWidth() / (float)bitmapWidth;
          float scaleY=(float)getContainerViewHeight() / (float)bitmapHeight;
          float newScaleX=(float)getContainerViewWidth(1) / (float)bitmapWidth;
          float newScaleY=(float)getContainerViewHeight(1) / (float)bitmapHeight;
          float scale=scaleX > scaleY ? scaleY : scaleX;
          float newScale=newScaleX > newScaleY ? newScaleY : newScaleX;
          if (sendPhotoType == SELECT_TYPE_AVATAR) {
            float minSide=Math.min(getContainerViewWidth(1),getContainerViewHeight(1));
            newScaleX=minSide / (float)bitmapWidth;
            newScaleY=minSide / (float)bitmapHeight;
            newScale=newScaleX > newScaleY ? newScaleX : newScaleY;
          }
          animateToScale=newScale / scale;
          animateToX=getLeftInset() / 2 - getRightInset() / 2;
          animateToY=-AndroidUtilities.dp(24 + 32) + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight / 2 : 0);
          animationStartTime=System.currentTimeMillis();
          zoomAnimation=true;
        }
        imageMoveAnimation=new AnimatorSet();
        imageMoveAnimation.playTogether(ObjectAnimator.ofFloat(editorDoneLayout,View.TRANSLATION_Y,AndroidUtilities.dp(48),0),ObjectAnimator.ofFloat(PhotoViewer.this,AnimationProperties.PHOTO_VIEWER_ANIMATION_VALUE,0,1),ObjectAnimator.ofFloat(photoCropView,View.ALPHA,0,1));
        imageMoveAnimation.setDuration(200);
        imageMoveAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationStart(          Animator animation){
            editorDoneLayout.setVisibility(View.VISIBLE);
            photoCropView.setVisibility(View.VISIBLE);
          }
          @Override public void onAnimationEnd(          Animator animation){
            photoCropView.onAppeared();
            imageMoveAnimation=null;
            currentEditMode=mode;
            animateToScale=1;
            animateToX=0;
            animateToY=0;
            scale=1;
            updateMinMax(scale);
            padImageForHorizontalInsets=true;
            containerView.invalidate();
          }
        }
);
        imageMoveAnimation.start();
      }
    }
);
    changeModeAnimation.start();
  }
 else   if (mode == 2) {
    if (photoFilterView == null) {
      MediaController.SavedFilterState state=null;
      Bitmap bitmap;
      String originalPath=null;
      int orientation=0;
      if (!imagesArrLocals.isEmpty()) {
        Object object=imagesArrLocals.get(currentIndex);
        if (object instanceof MediaController.PhotoEntry) {
          MediaController.PhotoEntry entry=(MediaController.PhotoEntry)object;
          if (entry.imagePath == null) {
            originalPath=entry.path;
            state=entry.savedFilterState;
          }
          orientation=entry.orientation;
        }
 else         if (object instanceof MediaController.SearchImage) {
          MediaController.SearchImage entry=(MediaController.SearchImage)object;
          state=entry.savedFilterState;
          originalPath=entry.imageUrl;
        }
      }
      if (state == null) {
        bitmap=centerImage.getBitmap();
        orientation=centerImage.getOrientation();
      }
 else {
        bitmap=BitmapFactory.decodeFile(originalPath);
      }
      photoFilterView=new PhotoFilterView(parentActivity,bitmap,orientation,state);
      containerView.addView(photoFilterView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
      photoFilterView.getDoneTextView().setOnClickListener(v -> {
        applyCurrentEditMode();
        switchToEditMode(0);
      }
);
      photoFilterView.getCancelTextView().setOnClickListener(v -> {
        if (photoFilterView.hasChanges()) {
          if (parentActivity == null) {
            return;
          }
          AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
          builder.setMessage(LocaleController.getString("DiscardChanges",R.string.DiscardChanges));
          builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
          builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> switchToEditMode(0));
          builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
          showAlertDialog(builder);
        }
 else {
          switchToEditMode(0);
        }
      }
);
      photoFilterView.getToolsView().setTranslationY(AndroidUtilities.dp(186));
    }
    changeModeAnimation=new AnimatorSet();
    ArrayList<Animator> arrayList=new ArrayList<>();
    arrayList.add(ObjectAnimator.ofFloat(pickerView,View.TRANSLATION_Y,0,AndroidUtilities.dp(96)));
    arrayList.add(ObjectAnimator.ofFloat(pickerViewSendButton,View.TRANSLATION_Y,0,AndroidUtilities.dp(96)));
    arrayList.add(ObjectAnimator.ofFloat(actionBar,View.TRANSLATION_Y,0,-actionBar.getHeight()));
    if (sendPhotoType == 0 || sendPhotoType == 4) {
      arrayList.add(ObjectAnimator.ofFloat(checkImageView,View.ALPHA,1,0));
      arrayList.add(ObjectAnimator.ofFloat(photosCounterView,View.ALPHA,1,0));
    }
 else     if (sendPhotoType == SELECT_TYPE_AVATAR) {
      arrayList.add(ObjectAnimator.ofFloat(photoCropView,View.ALPHA,1,0));
    }
    if (selectedPhotosListView.getVisibility() == View.VISIBLE) {
      arrayList.add(ObjectAnimator.ofFloat(selectedPhotosListView,View.ALPHA,1,0));
    }
    if (cameraItem.getTag() != null) {
      arrayList.add(ObjectAnimator.ofFloat(cameraItem,View.ALPHA,1,0));
    }
    changeModeAnimation.playTogether(arrayList);
    changeModeAnimation.setDuration(200);
    changeModeAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        changeModeAnimation=null;
        pickerView.setVisibility(View.GONE);
        pickerViewSendButton.setVisibility(View.GONE);
        actionBar.setVisibility(View.GONE);
        cameraItem.setVisibility(View.GONE);
        selectedPhotosListView.setVisibility(View.GONE);
        selectedPhotosListView.setAlpha(0.0f);
        selectedPhotosListView.setTranslationY(-AndroidUtilities.dp(10));
        photosCounterView.setRotationX(0.0f);
        selectedPhotosListView.setEnabled(false);
        isPhotosListViewVisible=false;
        if (needCaptionLayout) {
          captionTextView.setVisibility(View.INVISIBLE);
        }
        if (sendPhotoType == 0 || sendPhotoType == 4 || (sendPhotoType == 2 || sendPhotoType == 5) && imagesArrLocals.size() > 1) {
          checkImageView.setVisibility(View.GONE);
          photosCounterView.setVisibility(View.GONE);
        }
        Bitmap bitmap=centerImage.getBitmap();
        if (bitmap != null) {
          int bitmapWidth=centerImage.getBitmapWidth();
          int bitmapHeight=centerImage.getBitmapHeight();
          float scaleX=(float)getContainerViewWidth() / (float)bitmapWidth;
          float scaleY=(float)getContainerViewHeight() / (float)bitmapHeight;
          float newScaleX=(float)getContainerViewWidth(2) / (float)bitmapWidth;
          float newScaleY=(float)getContainerViewHeight(2) / (float)bitmapHeight;
          float scale=scaleX > scaleY ? scaleY : scaleX;
          float newScale=newScaleX > newScaleY ? newScaleY : newScaleX;
          animateToScale=newScale / scale;
          animateToX=getLeftInset() / 2 - getRightInset() / 2;
          animateToY=-AndroidUtilities.dp(92) + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight / 2 : 0);
          animationStartTime=System.currentTimeMillis();
          zoomAnimation=true;
        }
        imageMoveAnimation=new AnimatorSet();
        imageMoveAnimation.playTogether(ObjectAnimator.ofFloat(PhotoViewer.this,AnimationProperties.PHOTO_VIEWER_ANIMATION_VALUE,0,1),ObjectAnimator.ofFloat(photoFilterView.getToolsView(),View.TRANSLATION_Y,AndroidUtilities.dp(186),0));
        imageMoveAnimation.setDuration(200);
        imageMoveAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationStart(          Animator animation){
          }
          @Override public void onAnimationEnd(          Animator animation){
            photoFilterView.init();
            imageMoveAnimation=null;
            currentEditMode=mode;
            animateToScale=1;
            animateToX=0;
            animateToY=0;
            scale=1;
            updateMinMax(scale);
            padImageForHorizontalInsets=true;
            containerView.invalidate();
            if (sendPhotoType == SELECT_TYPE_AVATAR) {
              photoCropView.reset();
            }
          }
        }
);
        imageMoveAnimation.start();
      }
    }
);
    changeModeAnimation.start();
  }
 else   if (mode == 3) {
    if (photoPaintView == null) {
      photoPaintView=new PhotoPaintView(parentActivity,centerImage.getBitmap(),centerImage.getOrientation());
      containerView.addView(photoPaintView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
      photoPaintView.getDoneTextView().setOnClickListener(v -> {
        applyCurrentEditMode();
        switchToEditMode(0);
      }
);
      photoPaintView.getCancelTextView().setOnClickListener(v -> photoPaintView.maybeShowDismissalAlert(PhotoViewer.this,parentActivity,() -> switchToEditMode(0)));
      photoPaintView.getColorPicker().setTranslationY(AndroidUtilities.dp(126));
      photoPaintView.getToolsView().setTranslationY(AndroidUtilities.dp(126));
    }
    changeModeAnimation=new AnimatorSet();
    ArrayList<Animator> arrayList=new ArrayList<>();
    arrayList.add(ObjectAnimator.ofFloat(pickerView,View.TRANSLATION_Y,0,AndroidUtilities.dp(96)));
    arrayList.add(ObjectAnimator.ofFloat(pickerViewSendButton,View.TRANSLATION_Y,0,AndroidUtilities.dp(96)));
    arrayList.add(ObjectAnimator.ofFloat(actionBar,View.TRANSLATION_Y,0,-actionBar.getHeight()));
    if (needCaptionLayout) {
      arrayList.add(ObjectAnimator.ofFloat(captionTextView,View.TRANSLATION_Y,0,AndroidUtilities.dp(96)));
    }
    if (sendPhotoType == 0 || sendPhotoType == 4) {
      arrayList.add(ObjectAnimator.ofFloat(checkImageView,View.ALPHA,1,0));
      arrayList.add(ObjectAnimator.ofFloat(photosCounterView,View.ALPHA,1,0));
    }
 else     if (sendPhotoType == SELECT_TYPE_AVATAR) {
      arrayList.add(ObjectAnimator.ofFloat(photoCropView,View.ALPHA,1,0));
    }
    if (selectedPhotosListView.getVisibility() == View.VISIBLE) {
      arrayList.add(ObjectAnimator.ofFloat(selectedPhotosListView,View.ALPHA,1,0));
    }
    if (cameraItem.getTag() != null) {
      arrayList.add(ObjectAnimator.ofFloat(cameraItem,View.ALPHA,1,0));
    }
    changeModeAnimation.playTogether(arrayList);
    changeModeAnimation.setDuration(200);
    changeModeAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        changeModeAnimation=null;
        pickerView.setVisibility(View.GONE);
        pickerViewSendButton.setVisibility(View.GONE);
        cameraItem.setVisibility(View.GONE);
        selectedPhotosListView.setVisibility(View.GONE);
        selectedPhotosListView.setAlpha(0.0f);
        selectedPhotosListView.setTranslationY(-AndroidUtilities.dp(10));
        photosCounterView.setRotationX(0.0f);
        selectedPhotosListView.setEnabled(false);
        isPhotosListViewVisible=false;
        if (needCaptionLayout) {
          captionTextView.setVisibility(View.INVISIBLE);
        }
        if (sendPhotoType == 0 || sendPhotoType == 4 || (sendPhotoType == 2 || sendPhotoType == 5) && imagesArrLocals.size() > 1) {
          checkImageView.setVisibility(View.GONE);
          photosCounterView.setVisibility(View.GONE);
        }
        Bitmap bitmap=centerImage.getBitmap();
        if (bitmap != null) {
          int bitmapWidth=centerImage.getBitmapWidth();
          int bitmapHeight=centerImage.getBitmapHeight();
          float scaleX=(float)getContainerViewWidth() / (float)bitmapWidth;
          float scaleY=(float)getContainerViewHeight() / (float)bitmapHeight;
          float newScaleX=(float)getContainerViewWidth(3) / (float)bitmapWidth;
          float newScaleY=(float)getContainerViewHeight(3) / (float)bitmapHeight;
          float scale=scaleX > scaleY ? scaleY : scaleX;
          float newScale=newScaleX > newScaleY ? newScaleY : newScaleX;
          animateToScale=newScale / scale;
          animateToX=getLeftInset() / 2 - getRightInset() / 2;
          animateToY=-AndroidUtilities.dp(44) + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight / 2 : 0);
          animationStartTime=System.currentTimeMillis();
          zoomAnimation=true;
        }
        imageMoveAnimation=new AnimatorSet();
        imageMoveAnimation.playTogether(ObjectAnimator.ofFloat(PhotoViewer.this,AnimationProperties.PHOTO_VIEWER_ANIMATION_VALUE,0,1),ObjectAnimator.ofFloat(photoPaintView.getColorPicker(),View.TRANSLATION_Y,AndroidUtilities.dp(126),0),ObjectAnimator.ofFloat(photoPaintView.getToolsView(),View.TRANSLATION_Y,AndroidUtilities.dp(126),0));
        imageMoveAnimation.setDuration(200);
        imageMoveAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationStart(          Animator animation){
          }
          @Override public void onAnimationEnd(          Animator animation){
            photoPaintView.init();
            imageMoveAnimation=null;
            currentEditMode=mode;
            animateToScale=1;
            animateToX=0;
            animateToY=0;
            scale=1;
            updateMinMax(scale);
            padImageForHorizontalInsets=true;
            containerView.invalidate();
            if (sendPhotoType == SELECT_TYPE_AVATAR) {
              photoCropView.reset();
            }
          }
        }
);
        imageMoveAnimation.start();
      }
    }
);
    changeModeAnimation.start();
  }
}
