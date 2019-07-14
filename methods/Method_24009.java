protected int validateFramebuffer(){
  int status=checkFramebufferStatus(FRAMEBUFFER);
  if (status == FRAMEBUFFER_COMPLETE) {
    return 0;
  }
 else   if (status == FRAMEBUFFER_UNDEFINED) {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"framebuffer undefined"));
  }
 else   if (status == FRAMEBUFFER_INCOMPLETE_ATTACHMENT) {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"incomplete attachment"));
  }
 else   if (status == FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT) {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"incomplete missing attachment"));
  }
 else   if (status == FRAMEBUFFER_INCOMPLETE_DIMENSIONS) {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"incomplete dimensions"));
  }
 else   if (status == FRAMEBUFFER_INCOMPLETE_FORMATS) {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"incomplete formats"));
  }
 else   if (status == FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER) {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"incomplete draw buffer"));
  }
 else   if (status == FRAMEBUFFER_INCOMPLETE_READ_BUFFER) {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"incomplete read buffer"));
  }
 else   if (status == FRAMEBUFFER_UNSUPPORTED) {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"framebuffer unsupported"));
  }
 else   if (status == FRAMEBUFFER_INCOMPLETE_MULTISAMPLE) {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"incomplete multisample buffer"));
  }
 else   if (status == FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS) {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"incomplete layer targets"));
  }
 else {
    System.err.println(String.format(FRAMEBUFFER_ERROR,"unknown error " + status));
  }
  return status;
}
