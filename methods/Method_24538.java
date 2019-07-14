protected void imageImpl(PImage image,float x1,float y1,float x2,float y2,int u1,int v1,int u2,int v2){
  pushMatrix();
  translate(x1,y1);
  int imageWidth=image.width;
  int imageHeight=image.height;
  scale((x2 - x1) / imageWidth,(y2 - y1) / imageHeight);
  if (u2 - u1 == imageWidth && v2 - v1 == imageHeight) {
    g2.drawImage(image.getImage(),0,0,null);
  }
 else {
    PImage tmp=image.get(u1,v1,u2 - u1,v2 - v1);
    g2.drawImage((Image)tmp.getNative(),0,0,null);
  }
  popMatrix();
}
