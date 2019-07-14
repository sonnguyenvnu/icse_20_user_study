public static void b3InvertTransform(@NativeType("double const *") DoubleBuffer pos,@NativeType("double const *") DoubleBuffer orn,@NativeType("double *") DoubleBuffer outPos,@NativeType("double *") DoubleBuffer outOrn){
  if (CHECKS) {
    check(pos,3);
    check(orn,4);
    check(outPos,3);
    check(outOrn,4);
  }
  nb3InvertTransform(memAddress(pos),memAddress(orn),memAddress(outPos),memAddress(outOrn));
}
