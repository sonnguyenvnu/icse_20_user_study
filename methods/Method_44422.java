@Override public String toString(){
  String result="KrakenOHLCs";
  result+=" last=" + last + "]";
  for (  KrakenOHLC krakenOHLC : getOHLCs()) {
    result+="[OHLCs=" + krakenOHLC + "]";
  }
  return result;
}
