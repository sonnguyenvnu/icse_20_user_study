private static Canvas combineBitmap(Canvas cs,ArrayList<Bitmap> bpmList,boolean vertical){
  if (vertical) {
    int height=bpmList.get(0).getHeight();
    cs.drawBitmap(bpmList.get(0),0f,0f,null);
    for (int i=1; i < bpmList.size(); i++) {
      cs.drawBitmap(bpmList.get(i),0f,height,null);
      height+=bpmList.get(i).getHeight();
    }
    return cs;
  }
 else {
    int width=bpmList.get(0).getWidth();
    cs.drawBitmap(bpmList.get(0),0f,0f,null);
    for (int i=1; i < bpmList.size(); i++) {
      cs.drawBitmap(bpmList.get(i),width,0f,null);
      width+=bpmList.get(i).getWidth();
    }
    return cs;
  }
}
