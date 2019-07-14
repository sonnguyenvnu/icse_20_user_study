@Override public boolean filter(String obj){
  if (this.endsWith != null) {
    return obj.endsWith(this.endsWith);
  }
 else   if (this.pattern != null) {
    return this.pattern.matcher(obj).matches();
  }
 else {
    return false;
  }
}
