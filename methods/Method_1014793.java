/** 
 * Function to translate a point given in screen coordinates to world space. It's the same as GLU gluUnProject, but does not rely on OpenGL. The x- and y-coordinate of vec are assumed to be in screen coordinates (origin is the top left corner, y pointing down, x pointing to the right) as reported by the touch methods in  {@link Input}. A z-coordinate of 0 will return a point on the near plane, a z-coordinate of 1 will return a point on the far plane. This method allows you to specify the viewport position and dimensions in the coordinate system expected by  {@link GL20#glViewport(int,int,int,int)}, with the origin in the bottom left corner of the screen.
 * @param screenCoords the point in screen coordinates (origin top left)
 * @param viewportX the coordinate of the bottom left corner of the viewport in glViewport coordinates.
 * @param viewportY the coordinate of the bottom left corner of the viewport in glViewport coordinates.
 * @param viewportWidth the width of the viewport in pixels
 * @param viewportHeight the height of the viewport in pixels
 * @return the mutated and unprojected screenCoords {@link Vector3} 
 */
public Vector3 unproject(Vector3 screenCoords,float viewportX,float viewportY,float viewportWidth,float viewportHeight){
  float x=screenCoords.x, y=screenCoords.y;
  x=x - viewportX;
  y=gdxGraphicsHeight - y - 1;
  y=y - viewportY;
  screenCoords.x=(2 * x) / viewportWidth - 1;
  screenCoords.y=(2 * y) / viewportHeight - 1;
  screenCoords.z=2 * screenCoords.z - 1;
  screenCoords.prj(invProjectionView);
  return screenCoords;
}
