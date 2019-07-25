@SuppressLint("SetTextI18n") @Override public void bind(String preferenceKey,Long preferenceValue){
  super.bind(preferenceKey,preferenceValue);
  editTextValue.setText(preferenceValue.toString());
}
