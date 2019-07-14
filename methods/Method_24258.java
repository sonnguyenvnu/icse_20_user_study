@Override public void blitFramebuffer(int srcX0,int srcY0,int srcX1,int srcY1,int dstX0,int dstY0,int dstX1,int dstY1,int mask,int filter){
  if (gl2x != null) {
    gl2x.glBlitFramebuffer(srcX0,srcY0,srcX1,srcY1,dstX0,dstY0,dstX1,dstY1,mask,filter);
  }
 else   if (gl3 != null) {
    gl3.glBlitFramebuffer(srcX0,srcY0,srcX1,srcY1,dstX0,dstY0,dstX1,dstY1,mask,filter);
  }
 else   if (gl3es3 != null) {
    gl3es3.glBlitFramebuffer(srcX0,srcY0,srcX1,srcY1,dstX0,dstY0,dstX1,dstY1,mask,filter);
  }
 else {
    throw new RuntimeException(String.format(MISSING_GLFUNC_ERROR,"glBlitFramebuffer()"));
  }
}
