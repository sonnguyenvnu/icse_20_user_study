@SuppressWarnings("PMD.AvoidReassigningLoopVariables") public static int getChainAtOffset(HashedItem fpaux,long[] chainIndex,long[] isLastIndex,int offset){
  int nonEmptyChainsToSee=rank(isLastIndex[fpaux.set],offset);
  int nonEmptyChainSeen=rank(chainIndex[fpaux.set],nonEmptyChainsToSee);
  for (int i=nonEmptyChainsToSee; i <= 64; ) {
    if (TinySetIndexing.chainExist(chainIndex[fpaux.set],i) && (nonEmptyChainSeen == nonEmptyChainsToSee)) {
      return i;
    }
    i+=Math.max(1,nonEmptyChainsToSee - nonEmptyChainSeen);
    nonEmptyChainSeen=rank(chainIndex[fpaux.set],i);
  }
  throw new RuntimeException("Cannot choose victim!");
}
