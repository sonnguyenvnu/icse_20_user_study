@Override public void didUploadPhoto(final TLRPC.InputFile file,final TLRPC.PhotoSize bigSize,final TLRPC.PhotoSize smallSize){
  AndroidUtilities.runOnUIThread(() -> {
    if (file != null) {
      uploadedAvatar=file;
      if (createAfterUpload) {
        try {
          if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog=null;
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        donePressed=false;
        doneButton.performClick();
      }
      showAvatarProgress(false,true);
    }
 else {
      avatar=smallSize.location;
      avatarBig=bigSize.location;
      avatarImage.setImage(ImageLocation.getForLocal(avatar),"50_50",avatarDrawable,currentChat);
      showAvatarProgress(true,false);
    }
  }
);
}
