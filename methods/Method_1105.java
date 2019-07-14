private DrawableParent createDrawableParentForIndex(final int index){
  return new DrawableParent(){
    @Override public Drawable setDrawable(    Drawable newDrawable){
      return ArrayDrawable.this.setDrawable(index,newDrawable);
    }
    @Override public Drawable getDrawable(){
      return ArrayDrawable.this.getDrawable(index);
    }
  }
;
}
