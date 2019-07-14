@Override public int hashCode(){
  int result=updates;
  result=31 * result + followers;
  result=31 * result + favorites;
  result=31 * result + friends;
  return result;
}
