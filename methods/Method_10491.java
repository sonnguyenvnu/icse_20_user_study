public boolean matches(Size size){
  int gcd=gcd(size.getWidth(),size.getHeight());
  int x=size.getWidth() / gcd;
  int y=size.getHeight() / gcd;
  return mX == x && mY == y;
}
