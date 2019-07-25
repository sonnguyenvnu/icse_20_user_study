public void reset(final int width,final int height,float verticalViewAngleR){
  Log.i(TAG,String.format("AugmentedPanoramaEngine.reset(%d, %d, %f)",width,height,verticalViewAngleR));
synchronized (this.activatedSync) {
    this.width=width;
    this.height=height;
    this.halfWidth=this.width / 2;
    this.halfHeight=this.height / 2;
    if (verticalViewAngleR == 0)     verticalViewAngleR=45;
    this.verticalViewAngle=verticalViewAngleR;
    this.radius=(float)(this.halfWidth / Math.tan(Math.toRadians(verticalViewAngle / 2.0f)));
    this.radiusGL=(float)(this.halfHeight / Math.tan(Math.toRadians(verticalViewAngle / 2.0f)));
    final float HalfTileShiftAngle=(float)Math.atan2((0.5f - this.frameIntersectionPart / 2) * this.width,this.radius);
    this.radiusEdge=this.radius;
    final int circle_frames=(int)Math.ceil(2.0f * Math.PI / (2.0f * HalfTileShiftAngle));
    this.angleShift=(float)(2.0f * Math.PI / circle_frames);
    this.angleTotal=0.0f;
    final float[] vertices={-this.width / 2.0f,this.height / 2.0f,0.0f,this.width / 2.0f,this.height / 2.0f,0.0f,this.width / 2.0f,-this.height / 2.0f,0.0f,-this.width / 2.0f,-this.height / 2.0f,0.0f};
    final ByteBuffer byteBuf=ByteBuffer.allocateDirect(vertices.length * 4);
    byteBuf.order(ByteOrder.nativeOrder());
    this.vertexBuffer=byteBuf.asFloatBuffer();
    this.vertexBuffer.put(vertices);
    this.vertexBuffer.position(0);
    this.optimizeTextureDimensions();
    this.activated=true;
  }
  this.bCreateViewportNow=true;
}
