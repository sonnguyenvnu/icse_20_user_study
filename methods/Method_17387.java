public static int getChain(HashedItem fpaux,long[] chainIndex,long[] isLastIndex){
  int requiredChainNumber=rank(chainIndex[fpaux.set],fpaux.chainId);
  int currentChainNumber=rank(isLastIndex[fpaux.set],requiredChainNumber);
  int currentOffset=requiredChainNumber;
  long tempisLastIndex=isLastIndex[fpaux.set] >>> requiredChainNumber;
  while (currentChainNumber < requiredChainNumber) {
    currentChainNumber+=((int)tempisLastIndex) & 1;
    currentOffset++;
    tempisLastIndex>>>=1;
  }
  TinySetIndexing.chainStart=currentOffset;
  while ((tempisLastIndex & 1L) == 0) {
    currentOffset++;
    tempisLastIndex>>>=1;
  }
  TinySetIndexing.chainEnd=currentOffset;
  return currentOffset;
}
