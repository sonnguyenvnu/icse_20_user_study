/** 
 * Projects the  {@link Vector3} given in world space to screen coordinates. It's the same as GLU gluProject with one smalldeviation: The viewport is assumed to span the whole screen. The screen coordinate system has its origin in the <b>bottom</b> left, with the y-axis pointing <b>upwards</b> and the x-axis pointing to the right. This makes it easily useable in conjunction with  {@link Batch} and similar classes.
 * @return the mutated and projected worldCoords {@link Vector3} 
 */
public Vector3 project(Vector3 worldCoords){
  project(worldCoords,0,0,gdxGraphicsWidth,gdxGraphicsHeight);
  return worldCoords;
}
