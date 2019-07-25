protected void success(Downstream<? super T> downstream,T value){
  if (!fired) {
    fired=true;
    downstream.success(value);
  }
}
