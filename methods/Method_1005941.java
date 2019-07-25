@Override public void bind(String preferenceKey,Set<String> preferenceValue){
  super.bind(preferenceKey,preferenceValue);
  String displayValue=Arrays.toString(preferenceValue.toArray());
  editTextValue.setText(displayValue);
}
