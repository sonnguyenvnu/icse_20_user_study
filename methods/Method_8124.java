public void setSticker(TLRPC.Document document,Object parent,String emoji,boolean showEmoji){
  if (document != null) {
    sticker=document;
    parentObject=parent;
    TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
    if (thumb != null) {
      imageView.setImage(ImageLocation.getForDocument(thumb,document),null,"webp",null,parentObject);
    }
 else {
      imageView.setImage(ImageLocation.getForDocument(document),null,"webp",null,parentObject);
    }
    if (emoji != null) {
      emojiTextView.setText(Emoji.replaceEmoji(emoji,emojiTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(16),false));
      emojiTextView.setVisibility(VISIBLE);
    }
 else     if (showEmoji) {
      boolean set=false;
      for (int a=0; a < document.attributes.size(); a++) {
        TLRPC.DocumentAttribute attribute=document.attributes.get(a);
        if (attribute instanceof TLRPC.TL_documentAttributeSticker) {
          if (attribute.alt != null && attribute.alt.length() > 0) {
            emojiTextView.setText(Emoji.replaceEmoji(attribute.alt,emojiTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(16),false));
            set=true;
          }
          break;
        }
      }
      if (!set) {
        emojiTextView.setText(Emoji.replaceEmoji(DataQuery.getInstance(currentAccount).getEmojiForSticker(sticker.id),emojiTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(16),false));
      }
      emojiTextView.setVisibility(VISIBLE);
    }
 else {
      emojiTextView.setVisibility(INVISIBLE);
    }
  }
}
