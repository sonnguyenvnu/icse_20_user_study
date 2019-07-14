private void initPath(){
  path_srarch=new Path();
  path_circle=new Path();
  mMeasure=new PathMeasure();
  RectF oval1=new RectF(-50,-50,50,50);
  path_srarch.addArc(oval1,45,359.9f);
  RectF oval2=new RectF(-100,-100,100,100);
  path_circle.addArc(oval2,45,-359.9f);
  float[] pos=new float[2];
  mMeasure.setPath(path_circle,false);
  mMeasure.getPosTan(0,pos,null);
  path_srarch.lineTo(pos[0],pos[1]);
  Log.i("TAG","pos=" + pos[0] + ":" + pos[1]);
}
