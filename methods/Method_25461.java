/** 
 * Returns the edit distance between two strings. Levenshtein charges the same cost for each insertion or deletion. This algorithm is slightly more general in that it charges a sequence of adjacent insertions/deletions an up-front cost plus an incremental cost per insert/delete operation. The idea is that Christopher -&gt; Chris should be less than 6 times as expensive as Christopher -&gt; Christophe. The algorithm used to calculate this distance takes time and space proportional to the product of  {@code source.length()} and {@code target.length()} tobuild the 3 arrays.
 * @param source source string.
 * @param target target string
 * @param caseSensitive if true, case is used in comparisons and 'a' != 'A'.
 * @param changeCost cost of changing one character
 * @param openGapCost cost to open a gap to insert or delete some characters.
 * @param continueGapCost marginal cost to insert or delete next character.
 * @return edit distance between the source and target strings.
 */
public static int getEditDistance(String source,String target,boolean caseSensitive,int changeCost,int openGapCost,int continueGapCost){
  if (!caseSensitive) {
    source=Ascii.toLowerCase(source);
    target=Ascii.toLowerCase(target);
  }
  int sourceLength=source.length();
  int targetLength=target.length();
  if (sourceLength == 0) {
    return scriptCost(openGapCost,continueGapCost,targetLength);
  }
  if (targetLength == 0) {
    return scriptCost(openGapCost,continueGapCost,sourceLength);
  }
  int[][] mMatrix=new int[sourceLength + 1][targetLength + 1];
  int[][] dMatrix=new int[sourceLength + 1][targetLength + 1];
  int[][] iMatrix=new int[sourceLength + 1][targetLength + 1];
  mMatrix[0][0]=dMatrix[0][0]=iMatrix[0][0]=0;
  for (int i=1; i <= sourceLength; i++) {
    mMatrix[i][0]=dMatrix[i][0]=scriptCost(openGapCost,continueGapCost,i);
    iMatrix[i][0]=Integer.MAX_VALUE / 2;
  }
  for (int j=1; j <= targetLength; j++) {
    mMatrix[0][j]=iMatrix[0][j]=scriptCost(openGapCost,continueGapCost,j);
    dMatrix[0][j]=Integer.MAX_VALUE / 2;
  }
  for (int i=1; i <= sourceLength; i++) {
    char sourceI=source.charAt(i - 1);
    for (int j=1; j <= targetLength; j++) {
      char targetJ=target.charAt(j - 1);
      int cost=(sourceI == targetJ) ? 0 : changeCost;
      mMatrix[i][j]=cost + Ints.min(mMatrix[i - 1][j - 1],iMatrix[i - 1][j - 1],dMatrix[i - 1][j - 1]);
      dMatrix[i][j]=Math.min(mMatrix[i - 1][j] + openGapCost + continueGapCost,dMatrix[i - 1][j] + continueGapCost);
      iMatrix[i][j]=Math.min(mMatrix[i][j - 1] + openGapCost + continueGapCost,iMatrix[i][j - 1] + continueGapCost);
    }
  }
  int costOfEditScriptEndingWithMatch=mMatrix[sourceLength][targetLength];
  int costOfEditScriptEndingWithDelete=dMatrix[sourceLength][targetLength];
  int costOfEditScriptEndingWithInsert=iMatrix[sourceLength][targetLength];
  return Ints.min(costOfEditScriptEndingWithMatch,costOfEditScriptEndingWithDelete,costOfEditScriptEndingWithInsert);
}
