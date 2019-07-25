@SuppressLint("SetTextI18n") @Override public void bind(String preferenceKey,Integer preferenceValue){
  super.bind(preferenceKey,preferenceValue);
  editTextValue.setText(preferenceValue.toString());
}
