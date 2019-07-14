@Override protected void getImpl(int sourceX,int sourceY,int sourceWidth,int sourceHeight,PImage target,int targetX,int targetY){
  loadPixels();
  super.getImpl(sourceX,sourceY,sourceWidth,sourceHeight,target,targetX,targetY);
}
