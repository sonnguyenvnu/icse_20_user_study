public float dot(Vector other){
  float ret=0.0f;
  for (int i=0; i < size(); ++i) {
    ret+=elementArray[i] * other.elementArray[i];
  }
  return ret;
}
