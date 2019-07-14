/** 
 * Returns true of the given partitionId lies within this partition id range, else false.
 * @param partitionId
 * @return
 */
public boolean contains(int partitionId){
  if (lowerID < upperID) {
    return lowerID <= partitionId && upperID > partitionId;
  }
 else {
    return (lowerID <= partitionId && partitionId < idUpperBound) || (upperID > partitionId && partitionId >= 0);
  }
}
