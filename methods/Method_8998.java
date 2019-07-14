public void setFastScrollEnabled(){
  fastScroll=new FastScroll(getContext());
  if (getParent() != null) {
    ((ViewGroup)getParent()).addView(fastScroll);
  }
}
