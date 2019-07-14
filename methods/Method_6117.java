public static int setPeerLayerVersion(int layer,int version){
  return layer & 0x0000ffff | (version << 16);
}
