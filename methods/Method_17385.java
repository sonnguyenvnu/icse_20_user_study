public static int getChainStart(HashedItem fpaux,long[] chainIndex,long[] isLastIndex){
  int requiredChainNumber=rank(chainIndex[fpaux.set],fpaux.chainId);
  int currentChainNumber=rank(isLastIndex[fpaux.set],requiredChainNumber);
  int currentOffset=requiredChainNumber;
  long tempIsLastIndex=isLastIndex[fpaux.set] >>> requiredChainNumber;
  while (currentChainNumber < requiredChainNumber) {
    currentChainNumber+=((int)tempIsLastIndex) & 1;
    currentOffset++;
    tempIsLastIndex>>>=1;
  }
  return currentOffset;
}
