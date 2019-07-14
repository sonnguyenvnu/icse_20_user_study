public static boolean areMeasureSpecsEquivalent(int specA,int specB){
  return specA == specB || (SizeSpec.getMode(specA) == UNSPECIFIED && SizeSpec.getMode(specB) == UNSPECIFIED);
}
