public String wrap(String key,String fmt,String dft){
  String val=this.get(key);
  if (Strings.isBlank(val)) {
    return dft;
  }
  return String.format(fmt,val);
}
