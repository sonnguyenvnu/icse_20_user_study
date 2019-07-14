@Override public void onSuccessfullySubmitted(){
  showMessage(R.string.success,R.string.successfully_submitted);
  hideProgress();
  comment.setText("");
}
