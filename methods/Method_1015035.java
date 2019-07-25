public void bind(ChannelInfo channelInfo){
  channelNameTextView.setText(channelInfo.name);
  Glide.with(itemView.getContext()).load(channelInfo.portrait).into(portraitImageView);
}
