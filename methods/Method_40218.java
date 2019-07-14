@NotNull public static Type union(@NotNull Type u,@NotNull Type v){
  if (u.equals(v)) {
    return u;
  }
 else   if (u != Type.UNKNOWN && v == Type.UNKNOWN) {
    return u;
  }
 else   if (v != Type.UNKNOWN && u == Type.UNKNOWN) {
    return v;
  }
 else   if (u != Type.NONE && v == Type.NONE) {
    return u;
  }
 else   if (v != Type.NONE && v == Type.NONE) {
    return v;
  }
 else {
    return new UnionType(u,v);
  }
}
