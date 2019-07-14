public static int setMyLayerVersion(int layer,int version){
  return layer & 0xffff0000 | version;
}
