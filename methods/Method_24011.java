protected boolean hasFBOs(){
  int major=getGLVersion()[0];
  if (major < 2) {
    String ext=getString(EXTENSIONS);
    return ext.indexOf("_framebuffer_object") != -1 && ext.indexOf("_vertex_shader") != -1 && ext.indexOf("_shader_objects") != -1 && ext.indexOf("_shading_language") != -1;
  }
 else {
    return true;
  }
}
