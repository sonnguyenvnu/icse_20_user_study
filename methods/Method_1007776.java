@Override public Vector3 apply(Vector3 vector){
  return Vector3.at(vector.getX() * m00 + vector.getY() * m01 + vector.getZ() * m02 + m03,vector.getX() * m10 + vector.getY() * m11 + vector.getZ() * m12 + m13,vector.getX() * m20 + vector.getY() * m21 + vector.getZ() * m22 + m23);
}
