@Override public CharacterHit hit(double x,double y){
  try {
    return super.hit(x,y);
  }
 catch (  NoSuchElementException e) {
    return CharacterHit.insertionAt(0);
  }
}
