private String lab(Label label){
  String ret=labels.get(label);
  if (ret == null) {
    ret="L" + labCount++;
    labels.put(label,ret);
  }
  return ret;
}
