private static double redditCommentScore(final int ups,final int downs){
  final int n=ups + downs;
  if (0 == n) {
    return 0;
  }
  final double z=1.281551565545;
  final double p=(double)ups / n;
  return (p + z * z / (2 * n) - z * Math.sqrt((p * (1 - p) + z * z / (4 * n)) / n)) / (1 + z * z / n);
}
