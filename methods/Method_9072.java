private void setButton(View.OnClickListener onClickListener,String title,int color){
  pickerBottomLayout.setTextColor(color);
  pickerBottomLayout.setText(title.toUpperCase());
  pickerBottomLayout.setOnClickListener(onClickListener);
}
