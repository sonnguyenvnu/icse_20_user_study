/** 
 * Set's the node's type to the found Class in the node's name (if there is a class to be found).
 * @param node
 * @return The index in the array produced by splitting the node's name by '.', which is not part of theclass name found. Example: com.package.SomeClass.staticField.otherField, return would be 3
 */
private int searchNodeNameForClass(TypeNode node){
  int startIndex=node.getImage().split("\\.").length;
  for (String reducedImage=node.getImage(); ; ) {
    populateType(node,reducedImage);
    if (node.getType() != null) {
      break;
    }
    --startIndex;
    int lastDotIndex=reducedImage.lastIndexOf('.');
    if (lastDotIndex != -1) {
      reducedImage=reducedImage.substring(0,lastDotIndex);
    }
 else {
      break;
    }
  }
  return startIndex;
}
