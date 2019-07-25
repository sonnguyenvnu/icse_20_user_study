public static int brightness(int rgb){
  int r=(rgb >> 16) & 0xff;
  int g=(rgb >> 8) & 0xff;
  int b=rgb & 0xff;
  return (r + g + b) / 3;
}
