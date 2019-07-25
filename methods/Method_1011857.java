public static int dimc(SNode t){
{
    SNode matchedNode_48zlf8_a0d=t;
{
      boolean matches_48zlf8_a0a3=false;
{
        SNode matchingNode_48zlf8_a0a3=t;
        if (matchingNode_48zlf8_a0a3 != null) {
          matches_48zlf8_a0a3=matchingNode_48zlf8_a0a3.getConcept().isSubConceptOf(MetaAdapterFactory.getConcept(0x3304fc6e7c6b401eL,0xa016b944934bb21fL,0x42d5783a6267f776L,"jetbrains.mps.baseLanguage.math.structure.MatrixType"));
        }
      }
      if (matches_48zlf8_a0a3) {
        return SPropertyOperations.getInteger(matchedNode_48zlf8_a0d,MetaAdapterFactory.getProperty(0x3304fc6e7c6b401eL,0xa016b944934bb21fL,0x42d5783a6267f776L,0x42d5783a62683dadL,"columns"));
      }
 else {
        boolean matches_48zlf8_b0a3=false;
{
          SNode matchingNode_48zlf8_b0a3=t;
          if (matchingNode_48zlf8_b0a3 != null) {
            matches_48zlf8_b0a3=matchingNode_48zlf8_b0a3.getConcept().isSubConceptOf(MetaAdapterFactory.getConcept(0x3304fc6e7c6b401eL,0xa016b944934bb21fL,0x42d5783a6267f775L,"jetbrains.mps.baseLanguage.math.structure.VectorType"));
          }
        }
        if (matches_48zlf8_b0a3) {
          return 1;
        }
 else         return 0;
      }
    }
  }
}
