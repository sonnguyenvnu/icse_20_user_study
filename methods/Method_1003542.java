private boolean equals(TypeMirror mirror1,TypeMirror mirror2){
  if (mirror1 == null) {
    return (mirror2 == null);
  }
 else {
    return mirror2 != null && typeUtils.isSameType(mirror1,mirror2);
  }
}
