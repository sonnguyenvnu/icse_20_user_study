@Override public void initData(){
switch (type) {
case TYPE_REGISTER:
    tvBaseTitle.setText("??");
  break;
case TYPE_RESET:
tvBaseTitle.setText("????");
break;
default :
tvBaseTitle.setText("????");
break;
}
autoSetTitle();
llPasswordPassword.setVisibility(type == TYPE_VERIFY ? View.GONE : View.VISIBLE);
if (StringUtil.isNotEmpty(password,true)) {
etPasswordPassword0.setText(StringUtil.getString(password));
}
if (StringUtil.isPhone(phone)) {
etPasswordPhone.setText("" + phone);
getVerify();
}
}
