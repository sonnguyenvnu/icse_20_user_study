/** 
 * Transforms the specified world coordinate to screen coordinates.
 * @return The vector that was passed in, transformed to screen coordinates.
 * @see Camera#project(Vector3) 
 */
public Vector2 project(Vector2 worldCoords){
  tmp.set(worldCoords.x,worldCoords.y,1);
  camera.project(tmp,screenX,screenY,screenWidth,screenHeight);
  worldCoords.set(tmp.x,tmp.y);
  return worldCoords;
}
