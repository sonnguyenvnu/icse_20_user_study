public void append(byte b1,byte b2){
  expandIfNeeded(2);
  data[idx++]=b1;
  data[idx++]=b2;
}
