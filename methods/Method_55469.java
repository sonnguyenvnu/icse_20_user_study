public int hashCode(){
  return (int)(address ^ (address >>> 32));
}
