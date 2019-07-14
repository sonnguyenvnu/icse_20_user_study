public void setMonthType(Boolean isTrue){
  if (isTrue) {
    llType.setVisibility(View.INVISIBLE);
  }
 else {
    llType.setVisibility(View.VISIBLE);
  }
  mCheckBoxDay.setChecked(!isTrue);
}
