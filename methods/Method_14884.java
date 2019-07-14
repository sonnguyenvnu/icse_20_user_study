@Override public void finish(){
  user=getUser();
  user.setHead(StringUtil.getTrimedString(tvUserRemark));
  if (isOnEditMode && isDataChanged) {
    new AlertDialog(context,"","????????????",true,DIALOG_PUT_USER,this).show();
    return;
  }
  if (user != null) {
    CacheManager.getInstance().save(User.class,user,"" + user.getId(),"range=" + 0);
  }
  super.finish();
}
