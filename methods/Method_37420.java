@Override public String toString(){
  String out="";
  if (shortName != null) {
    out+="-" + shortName;
  }
  if (longName != null) {
    if (!out.isEmpty()) {
      out+=" | ";
    }
    out+="--" + longName;
  }
  return out;
}
