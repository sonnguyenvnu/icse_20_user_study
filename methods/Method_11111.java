private Paint getConfigPaint(Paint paint){
  if (enableFlashing) {
    paint.setColor(colorRandom[random.nextInt(colorCount - 1)]);
  }
  return paint;
}
