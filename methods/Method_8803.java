private float brushWeightForSize(float size){
  float paintingWidth=painting.getSize().width;
  return 8.0f / 2048.0f * paintingWidth + (90.0f / 2048.0f * paintingWidth) * size;
}
