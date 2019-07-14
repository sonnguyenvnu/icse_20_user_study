private void setRebroadcastedAttachmentImagesLayoutOnClickListener(Broadcast rebroadcastedBroadcast){
  if (rebroadcastedBroadcast.isDeleted) {
    mRebroadcastedAttachmentImagesLayout.setOnClickListener(null);
  }
 else {
    mRebroadcastedAttachmentImagesLayout.setOnClickListener(view -> {
      Context context=view.getContext();
      context.startActivity(BroadcastActivity.makeIntent(rebroadcastedBroadcast,context));
    }
);
  }
}
