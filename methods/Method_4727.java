@Override public int hashCode(){
  int result=(int)timeUs;
  result=31 * result + (int)position;
  return result;
}
