public boolean openPhotoForSelect(final ArrayList<Object> photos,final int index,int type,final PhotoViewerProvider provider,ChatActivity chatActivity){
  sendPhotoType=type;
  if (pickerViewSendButton != null) {
    FrameLayout.LayoutParams layoutParams2=(FrameLayout.LayoutParams)pickerViewSendButton.getLayoutParams();
    if (sendPhotoType == 4 || sendPhotoType == 5) {
      pickerViewSendButton.setImageResource(R.drawable.ic_send);
      pickerViewSendButton.setPadding(AndroidUtilities.dp(4),0,0,0);
      layoutParams2.bottomMargin=AndroidUtilities.dp(19);
    }
 else     if (sendPhotoType == SELECT_TYPE_AVATAR || sendPhotoType == SELECT_TYPE_WALLPAPER) {
      pickerViewSendButton.setImageResource(R.drawable.floating_check);
      pickerViewSendButton.setPadding(0,AndroidUtilities.dp(1),0,0);
      layoutParams2.bottomMargin=AndroidUtilities.dp(19);
    }
 else {
      pickerViewSendButton.setImageResource(R.drawable.ic_send);
      pickerViewSendButton.setPadding(AndroidUtilities.dp(4),0,0,0);
      layoutParams2.bottomMargin=AndroidUtilities.dp(14);
    }
    pickerViewSendButton.setLayoutParams(layoutParams2);
  }
  return openPhoto(null,null,null,null,photos,index,provider,chatActivity,0,0,true);
}
