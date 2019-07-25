/** 
 * Scales this steering acceleration by the specified scalar.
 * @param scalar the scalar
 * @return this steering acceleration for chaining 
 */
public SteeringAcceleration<T> scl(float scalar){
  linear.scl(scalar);
  angular*=scalar;
  return this;
}
