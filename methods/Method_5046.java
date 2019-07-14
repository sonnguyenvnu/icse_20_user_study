@Override public int hashCode(){
  int result=periodIndex;
  result=31 * result + groupIndex;
  result=31 * result + trackIndex;
  return result;
}
