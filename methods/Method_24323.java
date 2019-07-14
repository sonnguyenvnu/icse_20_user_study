protected void transform(int type,float... args){
  int dimensions=is3D ? 3 : 2;
  boolean invertible=true;
  checkMatrix(dimensions);
  if (transform == null) {
    if (dimensions == 2) {
      transform=new PMatrix2D();
      transformInv=new PMatrix2D();
    }
 else {
      transform=new PMatrix3D();
      transformInv=new PMatrix3D();
    }
  }
 else {
    transform.reset();
    transformInv.reset();
  }
  int ncoords=args.length;
  if (type == ROTATE) {
    ncoords=args.length == 1 ? 2 : 3;
  }
 else   if (type == MATRIX) {
    ncoords=args.length == 6 ? 2 : 3;
  }
switch (type) {
case TRANSLATE:
    if (ncoords == 3) {
      transform.translate(args[0],args[1],args[2]);
      PGraphicsOpenGL.invTranslate((PMatrix3D)transformInv,args[0],args[1],args[2]);
    }
 else {
      transform.translate(args[0],args[1]);
      PGraphicsOpenGL.invTranslate((PMatrix2D)transformInv,args[0],args[1]);
    }
  break;
case ROTATE:
if (ncoords == 3) {
  transform.rotate(args[0],args[1],args[2],args[3]);
  PGraphicsOpenGL.invRotate((PMatrix3D)transformInv,args[0],args[1],args[2],args[3]);
}
 else {
  transform.rotate(args[0]);
  PGraphicsOpenGL.invRotate((PMatrix2D)transformInv,-args[0]);
}
break;
case SCALE:
if (ncoords == 3) {
transform.scale(args[0],args[1],args[2]);
PGraphicsOpenGL.invScale((PMatrix3D)transformInv,args[0],args[1],args[2]);
}
 else {
transform.scale(args[0],args[1]);
PGraphicsOpenGL.invScale((PMatrix2D)transformInv,args[0],args[1]);
}
break;
case MATRIX:
if (ncoords == 3) {
transform.set(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15]);
}
 else {
transform.set(args[0],args[1],args[2],args[3],args[4],args[5]);
}
transformInv.set(transform);
invertible=transformInv.invert();
break;
}
matrix.preApply(transform);
if (invertible) {
matrixInv.apply(transformInv);
}
 else {
PGraphics.showWarning("Transformation applied on the shape cannot be inverted");
}
if (tessellated) applyMatrixImpl(transform);
}
