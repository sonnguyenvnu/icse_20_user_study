/** 
 * Adds a Collection of Strings to the MDAG.
 * @param strCollection a {@link java.util.Collection} containing Strings to be added to the MDAG
 */
public final void addStrings(Collection<String> strCollection){
  if (sourceNode != null) {
    String previousString="";
    for (    String currentString : strCollection) {
      int mpsIndex=calculateMinimizationProcessingStartIndex(previousString,currentString);
      if (mpsIndex != -1) {
        String transitionSubstring=previousString.substring(0,mpsIndex);
        String minimizationProcessingSubString=previousString.substring(mpsIndex);
        replaceOrRegister(sourceNode.transition(transitionSubstring),minimizationProcessingSubString);
      }
      addStringInternal(currentString);
      previousString=currentString;
    }
    replaceOrRegister(sourceNode,previousString);
  }
 else {
    unSimplify();
    addStrings(strCollection);
  }
}
