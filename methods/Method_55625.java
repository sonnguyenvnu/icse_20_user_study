private static <T extends Buffer>T realloc(@Nullable T old_p,T new_p,int size){
  if (old_p != null) {
    new_p.position(min(old_p.position(),size));
  }
  return new_p;
}
