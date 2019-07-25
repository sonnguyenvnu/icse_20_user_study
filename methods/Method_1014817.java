/** 
 * Calculates (this quaternion)^alpha where alpha is a real number and stores the result in this quaternion. See http://en.wikipedia.org/wiki/Quaternion#Exponential.2C_logarithm.2C_and_power
 * @param alpha Exponent
 * @return This quaternion for chaining 
 */
public Quaternion exp(float alpha){
  float norm=len();
  float normExp=(float)Math.pow(norm,alpha);
  float theta=(float)Math.acos(w / norm);
  float coeff=0;
  if (Math.abs(theta) < 0.001)   coeff=normExp * alpha / norm;
 else   coeff=(float)(normExp * Math.sin(alpha * theta) / (norm * Math.sin(theta)));
  w=(float)(normExp * Math.cos(alpha * theta));
  x*=coeff;
  y*=coeff;
  z*=coeff;
  nor();
  return this;
}
