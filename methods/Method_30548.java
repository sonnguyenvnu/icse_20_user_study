private static CharSequence appendParentText(CharSequence text,Broadcast parentBroadcast,Long parentBroadcastId,Context context){
  if (parentBroadcast == null && parentBroadcastId == null) {
    return text;
  }
  SpannableStringBuilder builder=SpannableStringBuilder.valueOf(text);
  if (parentBroadcast != null) {
    int parentSpaceStartIndex=builder.length();
    builder.append(" ");
    int parentSpaceEndIndex=builder.length();
    float spaceWidthEm=parentSpaceStartIndex > 0 ? 0.5f : -1f / 12;
    builder.setSpan(new SpaceSpan(spaceWidthEm),parentSpaceStartIndex,parentSpaceEndIndex,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    int parentIconStartIndex=builder.length();
    builder.append(" ");
    int parentIconEndIndex=builder.length();
    Drawable icon=AppCompatResources.getDrawable(context,R.drawable.rebroadcast_icon_white_18dp);
    icon=DrawableCompat.wrap(icon);
    DrawableCompat.setTint(icon,ViewUtils.getColorFromAttrRes(parentBroadcast.isDeleted ? android.R.attr.textColorSecondary : android.R.attr.textColorLink,0,context));
    builder.setSpan(new IconSpan(icon),parentIconStartIndex,parentIconEndIndex,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    if (parentBroadcast.isDeleted) {
      builder.append(context.getString(R.string.broadcast_rebroadcasted_broadcast_text_rebroadcast_deleted));
      int parentDeletedTextEndIndex=builder.length();
      builder.setSpan(new ForegroundColorSpan(ViewUtils.getColorFromAttrRes(android.R.attr.textColorSecondary,0,context)),parentSpaceStartIndex,parentDeletedTextEndIndex,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      parentBroadcastId=null;
    }
 else {
      builder.append(context.getString(R.string.broadcast_rebroadcasted_broadcast_text_rebroadcaster_format,parentBroadcast.author.name));
      int parentNameEndIndex=builder.length();
      builder.setSpan(new UriSpan(parentBroadcast.uri),parentSpaceStartIndex,parentNameEndIndex,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      builder.append(parentBroadcast.getTextWithEntities(false,context));
      parentBroadcastId=parentBroadcast.getParentBroadcastId();
    }
  }
  if (parentBroadcastId != null) {
    int parentSpaceStartIndex=builder.length();
    if (parentSpaceStartIndex > 0) {
      builder.append(" ");
      int parentSpaceEndIndex=builder.length();
      builder.setSpan(new SpaceSpan(0.5f),parentSpaceStartIndex,parentSpaceEndIndex,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    builder.append(context.getString(R.string.broadcast_rebroadcasted_broadcast_text_more_rebroadcast));
    int parentMoreEndIndex=builder.length();
    builder.setSpan(new UriSpan(DoubanUtils.makeBroadcastUri(parentBroadcastId)),parentSpaceStartIndex,parentMoreEndIndex,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  return builder;
}
