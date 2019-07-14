@Override public void onCheckType(boolean isOrg){
  if (!this.isOrg == isOrg) {
    startActivity(this,login,isOrg,isEnterprise(),index);
    finish();
  }
}
