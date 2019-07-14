private int randomColor(int rate){
  int red=random.nextInt(256) / rate;
  int green=random.nextInt(256) / rate;
  int blue=random.nextInt(256) / rate;
  return Color.rgb(red,green,blue);
}
