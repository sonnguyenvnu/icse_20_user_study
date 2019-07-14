int calculateShadowHeight(){
  return hasShadow() ? getShadowY() * 2 : 0;
}
