private static CatchTree getOnlyCatch(TryTree tryTree){
  return tryTree.getCatches().get(0);
}
