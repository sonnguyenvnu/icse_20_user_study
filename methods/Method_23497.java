public void setNormal(int index,float nx,float ny,float nz){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setNormal()");
    return;
  }
  vertices[index][PGraphics.NX]=nx;
  vertices[index][PGraphics.NY]=ny;
  vertices[index][PGraphics.NZ]=nz;
}
