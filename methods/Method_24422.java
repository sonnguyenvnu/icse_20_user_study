protected void initGL(){
  if (profile == null) {
    if (PJOGL.profile == 1) {
      try {
        profile=GLProfile.getGL2ES1();
      }
 catch (      GLException ex) {
        profile=GLProfile.getMaxFixedFunc(true);
      }
    }
 else     if (PJOGL.profile == 2) {
      try {
        profile=GLProfile.getGL2ES2();
        if (!profile.isHardwareRasterizer()) {
          GLProfile hardware=GLProfile.getMaxProgrammable(true);
          if (hardware.isGL2ES2()) {
            profile=hardware;
          }
        }
      }
 catch (      GLException ex) {
        profile=GLProfile.getMaxProgrammable(true);
      }
    }
 else     if (PJOGL.profile == 3) {
      try {
        profile=GLProfile.getGL2GL3();
      }
 catch (      GLException ex) {
        profile=GLProfile.getMaxProgrammable(true);
      }
      if (!profile.isGL3()) {
        PGraphics.showWarning("Requested profile GL3 but is not available, got: " + profile);
      }
    }
 else     if (PJOGL.profile == 4) {
      try {
        profile=GLProfile.getGL4ES3();
      }
 catch (      GLException ex) {
        profile=GLProfile.getMaxProgrammable(true);
      }
      if (!profile.isGL4()) {
        PGraphics.showWarning("Requested profile GL4 but is not available, got: " + profile);
      }
    }
 else     throw new RuntimeException(PGL.UNSUPPORTED_GLPROF_ERROR);
  }
  GLCapabilities caps=new GLCapabilities(profile);
  caps.setAlphaBits(PGL.REQUESTED_ALPHA_BITS);
  caps.setDepthBits(PGL.REQUESTED_DEPTH_BITS);
  caps.setStencilBits(PGL.REQUESTED_STENCIL_BITS);
  caps.setSampleBuffers(true);
  caps.setNumSamples(PGL.smoothToSamples(graphics.smooth));
  caps.setBackgroundOpaque(true);
  caps.setOnscreen(true);
  pgl.setCaps(caps);
}
