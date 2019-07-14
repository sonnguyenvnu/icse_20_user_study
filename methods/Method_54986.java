public static void b3MultiplyTransforms(@NativeType("double const *") DoubleBuffer posA,@NativeType("double const *") DoubleBuffer ornA,@NativeType("double const *") DoubleBuffer posB,@NativeType("double const *") DoubleBuffer ornB,@NativeType("double *") DoubleBuffer outPos,@NativeType("double *") DoubleBuffer outOrn){
  if (CHECKS) {
    check(posA,3);
    check(ornA,4);
    check(posB,3);
    check(ornB,4);
    check(outPos,3);
    check(outOrn,4);
  }
  nb3MultiplyTransforms(memAddress(posA),memAddress(ornA),memAddress(posB),memAddress(ornB),memAddress(outPos),memAddress(outOrn));
}
