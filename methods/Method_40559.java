public static Type union(Type u,Type v){
  if (u.equals(v)) {
    return u;
  }
 else   if (u.isUnknownType()) {
    return v;
  }
 else   if (v.isUnknownType()) {
    return u;
  }
 else {
    return new UnionType(u,v);
  }
}
