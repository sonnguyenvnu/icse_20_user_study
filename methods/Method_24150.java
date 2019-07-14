/** 
 * Set matrix mode to the camera matrix (instead of the current transformation matrix). This means applyMatrix, resetMatrix, etc. will affect the camera. <P> Note that the camera matrix is *not* the perspective matrix, it contains the values of the modelview matrix immediatly after the latter was initialized with ortho() or camera(), or the modelview matrix as result of the operations applied between beginCamera()/endCamera(). <P> beginCamera() specifies that all coordinate transforms until endCamera() should be pre-applied in inverse to the camera transform matrix. Note that this is only challenging when a user specifies an arbitrary matrix with applyMatrix(). Then that matrix will need to be inverted, which may not be possible. But take heart, if a user is applying a non-invertible matrix to the camera transform, then he is clearly up to no good, and we can wash our hands of those bad intentions. <P> begin/endCamera clauses do not automatically reset the camera transform matrix. That's because we set up a nice default camera transform in setup(), and we expect it to hold through draw(). So we don't reset the camera transform matrix at the top of draw(). That means that an innocuous-looking clause like <PRE> beginCamera(); translate(0, 0, 10); endCamera(); </PRE> at the top of draw(), will result in a runaway camera that shoots infinitely out of the screen over time. In order to prevent this, it is necessary to call some function that does a hard reset of the camera transform matrix inside of begin/endCamera. Two options are <PRE> camera(); // sets up the nice default camera transform resetMatrix(); // sets up the identity camera transform </PRE> So to rotate a camera a constant amount, you might try <PRE> beginCamera(); camera(); rotateY(PI / 8); endCamera(); </PRE>
 */
@Override public void beginCamera(){
  if (manipulatingCamera) {
    throw new RuntimeException("beginCamera() cannot be called again " + "before endCamera()");
  }
 else {
    manipulatingCamera=true;
  }
}
