private static int match(Class<?>[] params,Object[] values){
  int len=params.length;
  if (len == values.length) {
    int points=1;
    for (int i=0; i < len; i++) {
      Class<?> pc=getNonPrimitiveClass(params[i]);
      Object v=values[i];
      Class<?> vc=v == null ? null : v.getClass();
      if (pc == vc) {
        points++;
      }
 else       if (vc == null) {
      }
 else       if (!pc.isAssignableFrom(vc)) {
        return 0;
      }
    }
    return points;
  }
  return 0;
}
