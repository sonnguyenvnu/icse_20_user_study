private static void _XXXXX_(final IcnsType imageType,final byte[] imageData,final ImageBuilder image){
  int i=0;
  boolean visited=false;
  for (int y=0; y < imageType.getHeight(); y++) {
    for (int x=0; x < imageType.getWidth(); x++) {
      int index;
      if (!visited) {
        index=0xf & (imageData[i] >> 4);
      }
 else {
        index=0xf & imageData[i++];
      }
      visited=!visited;
      image.setRGB(x,y,PALETTE_4BPP[index]);
    }
  }
}