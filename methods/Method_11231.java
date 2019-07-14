private void randomTextStyle(Paint paint){
  int color=randomColor();
  paint.setColor(color);
  paint.setFakeBoldText(random.nextBoolean());
  float skewX=random.nextInt(11) / 10;
  skewX=random.nextBoolean() ? skewX : -skewX;
}
