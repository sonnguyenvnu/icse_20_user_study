private void alignProfileImageWithHeader(){
  headerImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
    @Override public void onGlobalLayout(){
      headerImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
      int profileImageSize=profileImage.getMeasuredHeight();
      int headerImageHeight=headerImage.getMeasuredHeight();
      LayoutParams layoutParams=(LayoutParams)profileImage.getLayoutParams();
      layoutParams.topMargin=headerImageHeight - (int)(0.5f * profileImageSize);
      profileImage.setLayoutParams(layoutParams);
    }
  }
);
}
