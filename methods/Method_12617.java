public int hashCode(){
  int absBegin=getBeginPosition();
  int absEnd=getEndPosition();
  return (absBegin * 37) + (absEnd * 31) + ((absBegin * absEnd) % getLength()) * 11;
}
