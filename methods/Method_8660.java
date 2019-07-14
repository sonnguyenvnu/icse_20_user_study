private Size sizeForItem(int i){
  Size size=getSizeForItem(i);
  if (size.width == 0) {
    size.width=100;
  }
  if (size.height == 0) {
    size.height=100;
  }
  float aspect=size.width / size.height;
  if (aspect > 4.0f || aspect < 0.2f) {
    size.height=size.width=Math.max(size.width,size.height);
  }
  return size;
}
