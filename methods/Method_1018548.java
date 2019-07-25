public byte[] digest(){
  byte[] tail=padBuffer();
  update(tail,0,tail.length);
  byte[] result=getResult();
  reset();
  return result;
}
