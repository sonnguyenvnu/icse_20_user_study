public void setChannel(TLRPC.Chat channel,boolean last){
  final String url=MessagesController.getInstance(currentAccount).linkPrefix + "/";
  currentChannel=channel;
  avatarDrawable.setInfo(channel);
  nameTextView.setText(channel.title);
  SpannableStringBuilder stringBuilder=new SpannableStringBuilder(url + channel.username);
  stringBuilder.setSpan(new URLSpanNoUnderline(""),url.length(),stringBuilder.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  statusTextView.setText(stringBuilder);
  avatarImageView.setImage(ImageLocation.getForChat(channel,false),"50_50",avatarDrawable,currentChannel);
  isLast=last;
}
