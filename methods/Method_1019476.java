static int pair(final int slotNo,final int value){
  return (value << KEY_BITS_26) | (slotNo & KEY_MASK_26);
}
