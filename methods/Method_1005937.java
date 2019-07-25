@Override public void bind(String preferenceKey,Boolean preferenceValue){
  super.bind(preferenceKey,preferenceValue);
  switchValue.setChecked(preferenceValue);
}
