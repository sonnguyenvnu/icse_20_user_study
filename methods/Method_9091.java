@Override protected void onDraw(Canvas canvas){
  for (int i=0, N=getChildCount(); i < N; i++) {
    Child c=getChildAt(i);
    c.draw(canvas);
  }
}
