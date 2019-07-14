/** 
 * ?????<br>
 * @param other
 * @return
 */
public float cosine(Vector other){
  return dot(other) / this.norm() / other.norm();
}
