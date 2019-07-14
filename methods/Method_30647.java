public void bind(User user,List<Broadcast> broadcastList){
  Context context=getContext();
  View.OnClickListener viewMoreListener=view -> context.startActivity(BroadcastListActivity.makeIntent(user,context));
  mTitleText.setOnClickListener(viewMoreListener);
  mViewMoreText.setOnClickListener(viewMoreListener);
  int i=0;
  for (  Broadcast broadcast : broadcastList) {
    if (i >= BROADCAST_COUNT_MAX) {
      break;
    }
    if (broadcast.rebroadcastedBroadcast != null) {
      continue;
    }
    if (i >= mBroadcastList.getChildCount()) {
      ViewUtils.inflateInto(R.layout.profile_broadcast_item,mBroadcastList);
    }
    View broadcastLayout=mBroadcastList.getChildAt(i);
    broadcastLayout.setVisibility(VISIBLE);
    BroadcastLayoutHolder holder=(BroadcastLayoutHolder)broadcastLayout.getTag();
    if (holder == null) {
      holder=new BroadcastLayoutHolder(broadcastLayout);
      broadcastLayout.setTag(holder);
      ViewUtils.setTextViewLinkClickable(holder.textText);
    }
    if (holder.boundBroadcastId != broadcast.id) {
      SizedImageItem image=null;
      if (broadcast.attachment != null) {
        image=broadcast.attachment.image;
      }
      if (image == null) {
        List<? extends SizedImageItem> images=broadcast.attachment != null && broadcast.attachment.imageList != null ? broadcast.attachment.imageList.images : broadcast.images;
        if (images.size() > 0) {
          image=images.get(0);
        }
      }
      if (image != null) {
        holder.image.setVisibility(VISIBLE);
        ImageUtils.loadImage(holder.image,image);
      }
 else {
        holder.image.setVisibility(GONE);
      }
      CharSequence text=broadcast.getTextWithEntities(context);
      if (TextUtils.isEmpty(text) && broadcast.attachment != null) {
        text=broadcast.attachment.title;
      }
      holder.textText.setText(text);
      boolean hasTime=!TextUtils.isEmpty(broadcast.createTime);
      ViewUtils.setVisibleOrGone(holder.timeText,hasTime);
      if (hasTime) {
        holder.timeText.setDoubanTime(broadcast.createTime);
      }
      ViewUtils.setVisibleOrGone(holder.timeActionSpace,hasTime);
      holder.actionText.setText(broadcast.action);
      broadcastLayout.setOnClickListener(view -> context.startActivity(BroadcastActivity.makeIntent(broadcast,context)));
      holder.boundBroadcastId=broadcast.id;
    }
    ++i;
  }
  ViewUtils.setVisibleOrGone(mBroadcastList,i != 0);
  ViewUtils.setVisibleOrGone(mEmptyView,i == 0);
  if (user.broadcastCount > i) {
    mViewMoreText.setText(context.getString(R.string.view_more_with_count_format,user.broadcastCount));
  }
 else {
    mViewMoreText.setVisibility(GONE);
  }
  for (int count=mBroadcastList.getChildCount(); i < count; ++i) {
    mBroadcastList.getChildAt(i).setVisibility(GONE);
  }
}
