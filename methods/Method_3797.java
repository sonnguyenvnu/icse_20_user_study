private static boolean hitTest(View child,float x,float y,float left,float top){
  return x >= left && x <= left + child.getWidth() && y >= top && y <= top + child.getHeight();
}
