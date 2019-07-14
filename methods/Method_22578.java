/** 
 * Tests whether the reference's index file indicated by referenceFile exists.
 * @return true if and only if the file denoted by referenceFile exists; falseotherwise.
 */
public boolean hasReference(){
  return referenceFile.exists();
}
