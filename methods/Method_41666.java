@Override public int hashCode(){
  return (hour + 1) ^ (minute + 1) ^ (second + 1);
}
