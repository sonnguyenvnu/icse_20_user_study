/** 
 * Returns whether the given bounding box is contained in this bounding box.
 * @param b The bounding box
 * @return Whether the given bounding box is contained 
 */
public boolean contains(BoundingBox b){
  return !isValid() || (min.x <= b.min.x && min.y <= b.min.y && min.z <= b.min.z && max.x >= b.max.x && max.y >= b.max.y && max.z >= b.max.z);
}
