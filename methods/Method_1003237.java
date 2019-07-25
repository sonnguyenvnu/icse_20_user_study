private void expand(int plus){
  data=Utils.copyBytes(data,(data.length + plus) * 2);
}
