protected void bindTyped(){
  if (currentPG == null) {
    setRenderer(primaryPG.getCurrentPG());
    loadAttributes();
    loadUniforms();
  }
  setCommonUniforms();
  if (-1 < vertexLoc)   pgl.enableVertexAttribArray(vertexLoc);
  if (-1 < colorLoc)   pgl.enableVertexAttribArray(colorLoc);
  if (-1 < texCoordLoc)   pgl.enableVertexAttribArray(texCoordLoc);
  if (-1 < normalLoc)   pgl.enableVertexAttribArray(normalLoc);
  if (-1 < normalMatLoc) {
    currentPG.updateGLNormal();
    setUniformMatrix(normalMatLoc,currentPG.glNormal);
  }
  if (-1 < ambientLoc)   pgl.enableVertexAttribArray(ambientLoc);
  if (-1 < specularLoc)   pgl.enableVertexAttribArray(specularLoc);
  if (-1 < emissiveLoc)   pgl.enableVertexAttribArray(emissiveLoc);
  if (-1 < shininessLoc)   pgl.enableVertexAttribArray(shininessLoc);
  int count=currentPG.lightCount;
  setUniformValue(lightCountLoc,count);
  if (0 < count) {
    setUniformVector(lightPositionLoc,currentPG.lightPosition,4,count);
    setUniformVector(lightNormalLoc,currentPG.lightNormal,3,count);
    setUniformVector(lightAmbientLoc,currentPG.lightAmbient,3,count);
    setUniformVector(lightDiffuseLoc,currentPG.lightDiffuse,3,count);
    setUniformVector(lightSpecularLoc,currentPG.lightSpecular,3,count);
    setUniformVector(lightFalloffLoc,currentPG.lightFalloffCoefficients,3,count);
    setUniformVector(lightSpotLoc,currentPG.lightSpotParameters,2,count);
  }
  if (-1 < directionLoc)   pgl.enableVertexAttribArray(directionLoc);
  if (-1 < offsetLoc)   pgl.enableVertexAttribArray(offsetLoc);
  if (-1 < perspectiveLoc) {
    if (currentPG.getHint(ENABLE_STROKE_PERSPECTIVE) && currentPG.nonOrthoProjection()) {
      setUniformValue(perspectiveLoc,1);
    }
 else {
      setUniformValue(perspectiveLoc,0);
    }
  }
  if (-1 < scaleLoc) {
    if (currentPG.getHint(DISABLE_OPTIMIZED_STROKE)) {
      setUniformValue(scaleLoc,1.0f,1.0f,1.0f);
    }
 else {
      float f=PGL.STROKE_DISPLACEMENT;
      if (currentPG.orthoProjection()) {
        setUniformValue(scaleLoc,1,1,f);
      }
 else {
        setUniformValue(scaleLoc,f,f,f);
      }
    }
  }
}
