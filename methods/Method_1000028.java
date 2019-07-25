public void negate(){
  if (this.isZero())   return;
  bnot();
  add(DataWord.ONE());
}
