private boolean isLeafValue(int value){
  return (value > 0) && ((value & LEAF_BIT) != 0);
}
