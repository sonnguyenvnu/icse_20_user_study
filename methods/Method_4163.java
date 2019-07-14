@Override public int hashCode(){
  int result=17;
  result=31 * result + contentType;
  result=31 * result + flags;
  result=31 * result + usage;
  return result;
}
