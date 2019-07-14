private void setUpBarBackground(){
  InsetDrawable insetDrawable;
  int resolveColor=resolveColor(getContext(),R.attr.colorControlNormal);
  insetDrawable=new InsetDrawable(new ColorDrawable(resolveColor),getResources().getDimensionPixelSize(R.dimen.fastscroller_track_padding),0,0,0);
  this.bar.setBackgroundDrawable(insetDrawable);
}
