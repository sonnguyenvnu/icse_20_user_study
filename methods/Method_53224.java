@Override public int hashCode(){
  int result=page;
  result=31 * result + count;
  result=31 * result + (int)(sinceId ^ (sinceId >>> 32));
  result=31 * result + (int)(maxId ^ (maxId >>> 32));
  return result;
}
