static protected PShape loadShapeImpl(PGraphics pg,String filename,String extension){
  PShapeOBJ obj=null;
  if (extension.equals("obj")) {
    obj=new PShapeOBJ(pg.parent,filename);
    int prevTextureMode=pg.textureMode;
    pg.textureMode=NORMAL;
    PShapeOpenGL p3d=PShapeOpenGL.createShape((PGraphicsOpenGL)pg,obj);
    pg.textureMode=prevTextureMode;
    return p3d;
  }
  return null;
}
