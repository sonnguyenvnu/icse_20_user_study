@Override public void initEvent(){
  llUserMoment.setOnClickListener(this);
  gvUserMoment.setOnTouchListener(new OnTouchListener(){
    @SuppressLint("ClickableViewAccessibility") @Override public boolean onTouch(    View v,    MotionEvent event){
      llUserMoment.onTouchEvent(event);
      return false;
    }
  }
);
  if (isOnEditMode) {
    findViewById(R.id.llUserRemark).setOnClickListener(this);
    findViewById(R.id.llUserPhone).setOnClickListener(this);
    userView.setOnDataChangedListener(new OnDataChangedListener(){
      @Override public void onDataChanged(){
        user=userView.getData();
        isDataChanged=true;
      }
    }
);
  }
  userView.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      if (user == null) {
        return;
      }
switch (v.getId()) {
case R.id.ivUserViewHead:
        if (isOnEditMode) {
          toActivity(SelectPictureActivity.createIntent(context),REQUEST_TO_SELECT_PICTURE,false);
        }
 else {
          toActivity(WebViewActivity.createIntent(context,user.getHead(),user.getHead()));
        }
      break;
case R.id.tvUserViewName:
    if (isOnEditMode) {
      toActivity(EditTextInfoWindow.createIntent(context,"??",user.getName()),REQUEST_TO_EDIT_TEXT_INFO_NAME,false);
    }
 else {
      CommonUtil.copyText(context,user.getName());
    }
  break;
default :
switch (v.getId()) {
case R.id.tvUserViewSex:
  if (isOnEditMode) {
    user.setSex(user.getSex() == User.SEX_FEMALE ? User.SEX_MAIL : User.SEX_FEMALE);
  }
break;
case R.id.tvUserViewTag:
if (isOnEditMode) {
toActivity(EditTextInfoWindow.createIntent(context,"??",user.getTag()),REQUEST_TO_EDIT_TEXT_INFO_TAG,false);
}
 else {
CommonUtil.copyText(context,user.getTag());
}
break;
default :
return;
}
if (isOnEditMode) {
userView.bindView(user);
isDataChanged=true;
}
break;
}
}
}
);
if (bottomMenuView != null) {
bottomMenuView.setOnMenuItemClickListener(this);
}
}
