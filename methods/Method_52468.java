@Override public String toString(){
  String nextString=next != null ? "(next: " + next + ")" : "";
  return "SimpleType:" + type + "/" + typeImage + nextString;
}
