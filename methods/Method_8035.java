public void setStickersSet(TLRPC.StickerSetCovered set,boolean divider,boolean unread){
  boolean sameSet=set == stickersSet && wasLayout;
  needDivider=divider;
  stickersSet=set;
  lastUpdateTime=System.currentTimeMillis();
  setWillNotDraw(!needDivider);
  if (currentAnimation != null) {
    currentAnimation.cancel();
    currentAnimation=null;
  }
  textView.setText(stickersSet.set.title);
  if (unread) {
    Drawable drawable=new Drawable(){
      @Override public void draw(      Canvas canvas){
        paint.setColor(0xff44a8ea);
        canvas.drawCircle(AndroidUtilities.dp(4),AndroidUtilities.dp(5),AndroidUtilities.dp(3),paint);
      }
      @Override public void setAlpha(      int alpha){
      }
      @Override public void setColorFilter(      ColorFilter colorFilter){
      }
      @Override public int getOpacity(){
        return PixelFormat.TRANSPARENT;
      }
      @Override public int getIntrinsicWidth(){
        return AndroidUtilities.dp(12);
      }
      @Override public int getIntrinsicHeight(){
        return AndroidUtilities.dp(8);
      }
    }
;
    textView.setCompoundDrawablesWithIntrinsicBounds(LocaleController.isRTL ? null : drawable,null,LocaleController.isRTL ? drawable : null,null);
  }
 else {
    textView.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
  }
  valueTextView.setText(LocaleController.formatPluralString("Stickers",set.set.count));
  TLRPC.PhotoSize thumb=set.cover != null ? FileLoader.getClosestPhotoSizeWithSize(set.cover.thumbs,90) : null;
  if (thumb != null && thumb.location != null) {
    imageView.setImage(ImageLocation.getForDocument(thumb,set.cover),null,"webp",null,set);
  }
 else   if (!set.covers.isEmpty()) {
    TLRPC.Document document=set.covers.get(0);
    thumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
    imageView.setImage(ImageLocation.getForDocument(thumb,document),null,"webp",null,set);
  }
 else {
    imageView.setImage(null,null,"webp",null,set);
  }
  if (sameSet) {
    boolean wasInstalled=isInstalled;
    if (isInstalled=DataQuery.getInstance(currentAccount).isStickerPackInstalled(set.set.id)) {
      if (!wasInstalled) {
        checkImage.setVisibility(VISIBLE);
        addButton.setClickable(false);
        currentAnimation=new AnimatorSet();
        currentAnimation.setDuration(200);
        currentAnimation.playTogether(ObjectAnimator.ofFloat(addButton,"alpha",1.0f,0.0f),ObjectAnimator.ofFloat(addButton,"scaleX",1.0f,0.01f),ObjectAnimator.ofFloat(addButton,"scaleY",1.0f,0.01f),ObjectAnimator.ofFloat(checkImage,"alpha",0.0f,1.0f),ObjectAnimator.ofFloat(checkImage,"scaleX",0.01f,1.0f),ObjectAnimator.ofFloat(checkImage,"scaleY",0.01f,1.0f));
        currentAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animator){
            if (currentAnimation != null && currentAnimation.equals(animator)) {
              addButton.setVisibility(INVISIBLE);
            }
          }
          @Override public void onAnimationCancel(          Animator animator){
            if (currentAnimation != null && currentAnimation.equals(animator)) {
              currentAnimation=null;
            }
          }
        }
);
        currentAnimation.start();
      }
    }
 else {
      if (wasInstalled) {
        addButton.setVisibility(VISIBLE);
        addButton.setClickable(true);
        currentAnimation=new AnimatorSet();
        currentAnimation.setDuration(200);
        currentAnimation.playTogether(ObjectAnimator.ofFloat(checkImage,"alpha",1.0f,0.0f),ObjectAnimator.ofFloat(checkImage,"scaleX",1.0f,0.01f),ObjectAnimator.ofFloat(checkImage,"scaleY",1.0f,0.01f),ObjectAnimator.ofFloat(addButton,"alpha",0.0f,1.0f),ObjectAnimator.ofFloat(addButton,"scaleX",0.01f,1.0f),ObjectAnimator.ofFloat(addButton,"scaleY",0.01f,1.0f));
        currentAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animator){
            if (currentAnimation != null && currentAnimation.equals(animator)) {
              checkImage.setVisibility(INVISIBLE);
            }
          }
          @Override public void onAnimationCancel(          Animator animator){
            if (currentAnimation != null && currentAnimation.equals(animator)) {
              currentAnimation=null;
            }
          }
        }
);
        currentAnimation.start();
      }
    }
  }
 else {
    if (isInstalled=DataQuery.getInstance(currentAccount).isStickerPackInstalled(set.set.id)) {
      addButton.setVisibility(INVISIBLE);
      addButton.setClickable(false);
      checkImage.setVisibility(VISIBLE);
      checkImage.setScaleX(1.0f);
      checkImage.setScaleY(1.0f);
      checkImage.setAlpha(1.0f);
    }
 else {
      addButton.setVisibility(VISIBLE);
      addButton.setClickable(true);
      checkImage.setVisibility(INVISIBLE);
      addButton.setScaleX(1.0f);
      addButton.setScaleY(1.0f);
      addButton.setAlpha(1.0f);
    }
  }
}
