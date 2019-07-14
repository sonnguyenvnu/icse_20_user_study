@OnLongClick(R.id.date) boolean onShowDateHint(){
  showMessage(R.string.creation_date,R.string.creation_date_hint);
  return true;
}
