/** 
 * Return the length of the shortest string in the array. If the collection is empty or any one of them is null then it returns 0.
 * @param strings String[]
 * @return int
 */
public static int lengthOfShortestIn(String[] strings){
  if (CollectionUtil.isEmpty(strings)) {
    return 0;
  }
  int minLength=Integer.MAX_VALUE;
  for (int i=0; i < strings.length; i++) {
    if (strings[i] == null) {
      return 0;
    }
    minLength=Math.min(minLength,strings[i].length());
  }
  return minLength;
}
