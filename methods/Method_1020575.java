@Override protected Dannmaku produce(Dannmaku dannmaku){
  float vx=-(20 + random.nextInt(30)) * VY_MULTIPLIER;
  float y=30 + random.nextInt(height / 2);
  if (dannmaku == null) {
    dannmaku=new Dannmaku(new PointF(width,y),vx);
  }
 else {
    dannmaku.reset(width,y,vx);
  }
  dannmaku.setText(WORDS[random.nextInt(WORDS.length)]);
  dannmaku.setColor(COLORS[random.nextInt(COLORS.length)]);
  return dannmaku;
}
