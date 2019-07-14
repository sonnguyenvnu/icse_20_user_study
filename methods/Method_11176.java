@Override public Sprite[] onCreateChild(){
  int delays[]=new int[]{200,300,400,100,200,300,0,100,200};
  GridItem[] gridItems=new GridItem[9];
  for (int i=0; i < gridItems.length; i++) {
    gridItems[i]=new GridItem();
    gridItems[i].setAnimationDelay(delays[i]);
  }
  return gridItems;
}
