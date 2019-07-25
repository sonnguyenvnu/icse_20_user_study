/** 
 * {@inheritDoc}
 */
@Override public boolean contains(final int x,final int y){
  return fadeStateType != FadeStateType.fadeOut && provideShape().contains(x,y);
}
