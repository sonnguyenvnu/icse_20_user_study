public static void removeItem(HashedItem fpaux,long[] chainIndex,long[] isLastIndex){
  int chainStart=getChainStart(fpaux,chainIndex,isLastIndex);
  chainIndex[fpaux.set]=(isLastIndex[fpaux.set] & (1L << chainStart)) == 0L ? chainIndex[fpaux.set] : chainIndex[fpaux.set] & ~(1L << fpaux.chainId);
  isLastIndex[fpaux.set]=shrinkOffset(isLastIndex[fpaux.set],chainStart);
}
