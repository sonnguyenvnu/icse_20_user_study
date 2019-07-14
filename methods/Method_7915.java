public void setOnCheckClick(Switch.OnCheckedChangeListener listener){
  checkBox.setOnCheckedChangeListener(onCheckedChangeListener=listener);
  checkBox.setOnClickListener(v -> checkBox.setChecked(!checkBox.isChecked(),true));
}
