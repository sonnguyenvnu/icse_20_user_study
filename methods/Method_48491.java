/** 
 * Returns a random partition id that lies within this partition id range.
 * @return
 */
public int getRandomID(){
  int partitionWidth;
  if (lowerID < upperID)   partitionWidth=upperID - lowerID;
 else   partitionWidth=(idUpperBound - lowerID) + upperID;
  Preconditions.checkArgument(partitionWidth > 0,partitionWidth);
  return (random.nextInt(partitionWidth) + lowerID) % idUpperBound;
}
