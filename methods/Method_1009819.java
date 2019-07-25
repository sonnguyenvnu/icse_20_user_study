public static boolean match(int r,int g,int b,int rt,int gt,int bt,int t){
  return r > rt - t && r < rt + t && g > gt - t && g < gt + t && b > bt - t && b < bt + t;
}
