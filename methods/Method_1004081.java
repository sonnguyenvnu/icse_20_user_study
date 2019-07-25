private static LabelInfo create(final Label label){
  LabelInfo info=get(label);
  if (info == null) {
    info=new LabelInfo();
    label.info=info;
  }
  return info;
}
