private void adjustHeight(int height){
  ViewGroup.LayoutParams ll=getLayoutParams();
  if (height != ll.height) {
    ll.height=height;
    setLayoutParams(ll);
  }
}
