/** 
 * ( begin auto-generated from PVector_mag.xml ) Calculates the squared magnitude of the vector and returns the result as a float (this is simply the equation <em>(x*x + y*y + z*z)</em>.) Faster if the real length is not required in the case of comparing vectors, etc. ( end auto-generated )
 * @webref pvector:method
 * @usage web_application
 * @brief Calculate the magnitude of the vector, squared
 * @return squared magnitude of the vector
 * @see PVector#mag()
 */
public float magSq(){
  return (x * x + y * y + z * z);
}
