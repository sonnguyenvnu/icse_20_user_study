private StringBuffer appendPath(StringBuffer s,MP4Box<?> box){
  if (box.getParent() != null) {
    appendPath(s,box.getParent());
    s.append("/");
  }
  return s.append(box.getType());
}
