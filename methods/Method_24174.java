@Override protected void backgroundImpl(PImage image){
  backgroundImpl();
  set(0,0,image);
  backgroundA=1;
  loaded=false;
}
