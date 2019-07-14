@Override public void bindView(User data){
  if (data == null) {
    Log.e(TAG,"bindView data == null >> data = new User(); ");
    data=new User();
  }
  this.data=data;
  ImageLoaderUtil.loadImage(ivUserViewHead,data.getHead(),ImageLoaderUtil.TYPE_OVAL);
  tvUserViewSex.setBackgroundResource(data.getSex() == User.SEX_FEMALE ? R.drawable.circle_pink : R.drawable.circle_blue);
  tvUserViewSex.setText(data.getSex() == User.SEX_FEMALE ? "?" : "?");
  tvUserViewSex.setTextColor(getColor(data.getSex() == User.SEX_FEMALE ? R.color.pink : R.color.blue));
  tvUserViewName.setText(StringUtil.getTrimedString(data.getName()));
  tvUserViewId.setText("ID:" + data.getId());
  tvUserViewTag.setText("Tag:" + StringUtil.getTrimedString(data.getTag()));
}
