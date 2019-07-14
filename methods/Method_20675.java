@Override public boolean isSet(){
  return this.sharedPreferences.contains(this.key);
}
