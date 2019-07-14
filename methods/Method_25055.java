int calculateShadowWidth(){
  return hasShadow() ? getShadowX() * 2 : 0;
}
