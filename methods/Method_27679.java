@OnCheckedChanged(R.id.accessTokenCheckbox) void onCheckChanged(boolean checked){
  isBasicAuth=!checked;
  password.setHint(checked ? getString(R.string.access_token) : getString(R.string.password));
}
