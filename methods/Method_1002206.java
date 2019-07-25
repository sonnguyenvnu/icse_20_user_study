public static int compare(String leftITag,String rightITag){
  for (  List<String> iTags : sITagsContainer) {
    int left=iTags.indexOf(leftITag);
    int right=iTags.indexOf(rightITag);
    if (left != -1 && right != -1) {
      return left - right;
    }
  }
  return 99;
}
