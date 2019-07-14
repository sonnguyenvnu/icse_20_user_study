protected void fillEdges(int x,int y,int w,int h){
  if ((width < glWidth || height < glHeight) && (x + w == width || y + h == height)) {
    if (x + w == width) {
      int ew=glWidth - width;
      edgePixels=new int[h * ew];
      for (int i=0; i < h; i++) {
        int c=rgbaPixels[i * w + (w - 1)];
        Arrays.fill(edgePixels,i * ew,(i + 1) * ew,c);
      }
      edgeBuffer=PGL.updateIntBuffer(edgeBuffer,edgePixels,true);
      pgl.texSubImage2D(glTarget,0,width,y,ew,h,PGL.RGBA,PGL.UNSIGNED_BYTE,edgeBuffer);
    }
    if (y + h == height) {
      int eh=glHeight - height;
      edgePixels=new int[eh * w];
      for (int i=0; i < eh; i++) {
        System.arraycopy(rgbaPixels,(h - 1) * w,edgePixels,i * w,w);
      }
      edgeBuffer=PGL.updateIntBuffer(edgeBuffer,edgePixels,true);
      pgl.texSubImage2D(glTarget,0,x,height,w,eh,PGL.RGBA,PGL.UNSIGNED_BYTE,edgeBuffer);
    }
    if (x + w == width && y + h == height) {
      int ew=glWidth - width;
      int eh=glHeight - height;
      int c=rgbaPixels[w * h - 1];
      edgePixels=new int[eh * ew];
      Arrays.fill(edgePixels,0,eh * ew,c);
      edgeBuffer=PGL.updateIntBuffer(edgeBuffer,edgePixels,true);
      pgl.texSubImage2D(glTarget,0,width,height,ew,eh,PGL.RGBA,PGL.UNSIGNED_BYTE,edgeBuffer);
    }
  }
}
