private void needShowProgress(){
  if (getParentActivity() == null || getParentActivity().isFinishing() || progressDialog != null) {
    return;
  }
  progressDialog=new AlertDialog(getParentActivity(),3);
  progressDialog.setCanCacnel(false);
  progressDialog.show();
}
