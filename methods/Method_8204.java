private void updatePrivatePublic(){
  if (sectionCell == null) {
    return;
  }
  if (!isPrivate && !canCreatePublic) {
    typeInfoCell.setText(LocaleController.getString("ChangePublicLimitReached",R.string.ChangePublicLimitReached));
    typeInfoCell.setTag(Theme.key_windowBackgroundWhiteRedText4);
    typeInfoCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
    linkContainer.setVisibility(View.GONE);
    sectionCell.setVisibility(View.GONE);
    if (loadingAdminedChannels) {
      loadingAdminedCell.setVisibility(View.VISIBLE);
      adminnedChannelsLayout.setVisibility(View.GONE);
      typeInfoCell.setBackgroundDrawable(Theme.getThemedDrawable(typeInfoCell.getContext(),R.drawable.greydivider_bottom,Theme.key_windowBackgroundGrayShadow));
      adminedInfoCell.setVisibility(View.GONE);
    }
 else {
      typeInfoCell.setBackgroundDrawable(Theme.getThemedDrawable(typeInfoCell.getContext(),R.drawable.greydivider,Theme.key_windowBackgroundGrayShadow));
      loadingAdminedCell.setVisibility(View.GONE);
      adminnedChannelsLayout.setVisibility(View.VISIBLE);
      adminedInfoCell.setVisibility(View.VISIBLE);
    }
  }
 else {
    typeInfoCell.setTag(Theme.key_windowBackgroundWhiteGrayText4);
    typeInfoCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
    sectionCell.setVisibility(View.VISIBLE);
    adminedInfoCell.setVisibility(View.GONE);
    adminnedChannelsLayout.setVisibility(View.GONE);
    typeInfoCell.setBackgroundDrawable(Theme.getThemedDrawable(typeInfoCell.getContext(),R.drawable.greydivider_bottom,Theme.key_windowBackgroundGrayShadow));
    linkContainer.setVisibility(View.VISIBLE);
    loadingAdminedCell.setVisibility(View.GONE);
    typeInfoCell.setText(isPrivate ? LocaleController.getString("ChannelPrivateLinkHelp",R.string.ChannelPrivateLinkHelp) : LocaleController.getString("ChannelUsernameHelp",R.string.ChannelUsernameHelp));
    headerCell.setText(isPrivate ? LocaleController.getString("ChannelInviteLinkTitle",R.string.ChannelInviteLinkTitle) : LocaleController.getString("ChannelLinkTitle",R.string.ChannelLinkTitle));
    publicContainer.setVisibility(isPrivate ? View.GONE : View.VISIBLE);
    privateContainer.setVisibility(isPrivate ? View.VISIBLE : View.GONE);
    linkContainer.setPadding(0,0,0,isPrivate ? 0 : AndroidUtilities.dp(7));
    privateContainer.setText(invite != null ? invite.link : LocaleController.getString("Loading",R.string.Loading),false);
    checkTextView.setVisibility(!isPrivate && checkTextView.length() != 0 ? View.VISIBLE : View.GONE);
  }
  radioButtonCell1.setChecked(!isPrivate,true);
  radioButtonCell2.setChecked(isPrivate,true);
  descriptionTextView.clearFocus();
  AndroidUtilities.hideKeyboard(descriptionTextView);
}
