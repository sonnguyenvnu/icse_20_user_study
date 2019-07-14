@OnClick({R.id.iv_clean_phone,R.id.clean_password,R.id.iv_show_pwd}) public void onViewClicked(View view){
switch (view.getId()) {
case R.id.iv_clean_phone:
    mEtMobile.setText("");
  break;
case R.id.clean_password:
mEtPassword.setText("");
break;
case R.id.iv_show_pwd:
if (mEtPassword.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
mEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
mIvShowPwd.setImageResource(R.drawable.pass_visuable);
}
 else {
mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
mIvShowPwd.setImageResource(R.drawable.pass_gone);
}
String pwd=mEtPassword.getText().toString();
if (!TextUtils.isEmpty(pwd)) mEtPassword.setSelection(pwd.length());
break;
}
}
