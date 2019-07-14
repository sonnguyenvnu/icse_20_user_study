@Override public void backgroundImpl(){
  modified=false;
  loaded=false;
  context.save();
  context.setTransform(new Affine());
  context.setFill(new Color(backgroundR,backgroundG,backgroundB,backgroundA));
  context.setGlobalBlendMode(BlendMode.SRC_OVER);
  context.fillRect(0,0,width,height);
  context.restore();
}
