@Override public void didUploadPhoto(final TLRPC.InputFile file,final TLRPC.PhotoSize bigSize,final TLRPC.PhotoSize smallSize){
  AndroidUtilities.runOnUIThread(() -> {
    if (file != null) {
      uploadedAvatar=file;
      if (createAfterUpload) {
        if (delegate != null) {
          delegate.didStartChatCreation();
        }
        MessagesController.getInstance(currentAccount).createChat(editText.getText().toString(),selectedContacts,null,chatType,GroupCreateFinalActivity.this);
      }
      showAvatarProgress(false,true);
      avatarEditor.setImageDrawable(null);
    }
 else {
      avatar=smallSize.location;
      avatarBig=bigSize.location;
      avatarImage.setImage(ImageLocation.getForLocal(avatar),"50_50",avatarDrawable,null);
      showAvatarProgress(true,false);
    }
  }
);
}
