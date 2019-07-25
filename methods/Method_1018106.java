@Override public boolean validate(String value){
  int len=value.length();
  return (len <= maxLength && len >= minLength);
}
