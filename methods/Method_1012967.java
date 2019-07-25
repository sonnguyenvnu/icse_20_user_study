private void exit(){
  Intent result=new Intent();
  setResult(Activity.RESULT_OK,result);
  super.onBackPressed();
}
