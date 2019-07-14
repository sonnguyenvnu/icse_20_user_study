private void updatePasswordInterface(){
  if (noPasswordImageView == null) {
    return;
  }
  if (currentPassword == null || usingSavedPassword != 0) {
    noPasswordImageView.setVisibility(View.GONE);
    noPasswordTextView.setVisibility(View.GONE);
    noPasswordSetTextView.setVisibility(View.GONE);
    passwordAvatarContainer.setVisibility(View.GONE);
    inputFieldContainers[FIELD_PASSWORD].setVisibility(View.GONE);
    doneItem.setVisibility(View.GONE);
    passwordForgotButton.setVisibility(View.GONE);
    passwordInfoRequestTextView.setVisibility(View.GONE);
    passwordRequestTextView.setVisibility(View.GONE);
    emptyView.setVisibility(View.VISIBLE);
  }
 else   if (!currentPassword.has_password) {
    passwordRequestTextView.setVisibility(View.VISIBLE);
    noPasswordImageView.setVisibility(View.VISIBLE);
    noPasswordTextView.setVisibility(View.VISIBLE);
    noPasswordSetTextView.setVisibility(View.VISIBLE);
    passwordAvatarContainer.setVisibility(View.GONE);
    inputFieldContainers[FIELD_PASSWORD].setVisibility(View.GONE);
    doneItem.setVisibility(View.GONE);
    passwordForgotButton.setVisibility(View.GONE);
    passwordInfoRequestTextView.setVisibility(View.GONE);
    passwordRequestTextView.setLayoutParams(LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,0,25,0,0));
    emptyView.setVisibility(View.GONE);
  }
 else {
    passwordRequestTextView.setVisibility(View.VISIBLE);
    noPasswordImageView.setVisibility(View.GONE);
    noPasswordTextView.setVisibility(View.GONE);
    noPasswordSetTextView.setVisibility(View.GONE);
    emptyView.setVisibility(View.GONE);
    passwordAvatarContainer.setVisibility(View.VISIBLE);
    inputFieldContainers[FIELD_PASSWORD].setVisibility(View.VISIBLE);
    doneItem.setVisibility(View.VISIBLE);
    passwordForgotButton.setVisibility(View.VISIBLE);
    passwordInfoRequestTextView.setVisibility(View.VISIBLE);
    passwordRequestTextView.setLayoutParams(LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,0,0,0,0));
    if (inputFields != null) {
      if (currentPassword != null && !TextUtils.isEmpty(currentPassword.hint)) {
        inputFields[FIELD_PASSWORD].setHint(currentPassword.hint);
      }
 else {
        inputFields[FIELD_PASSWORD].setHint(LocaleController.getString("LoginPassword",R.string.LoginPassword));
      }
    }
  }
}
