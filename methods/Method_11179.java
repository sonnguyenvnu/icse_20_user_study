@Override public Sprite[] onCreateChild(){
  Cube[] cubes=new Cube[4];
  for (int i=0; i < cubes.length; i++) {
    cubes[i]=new Cube();
    cubes[i].setAnimationDelay(300 * i - 1200);
  }
  return cubes;
}
