@Override public int hashCode(){
  int result=(int)(id ^ (id >>> 32));
  result=31 * result + getViewType();
  result=31 * result + (shown ? 1 : 0);
  return result;
}
