@Override public void layout(int l,int t,int r,int b){
  View p=(View)getParent();
  if (p != null) {
    int pw=p.getMeasuredWidth();
    int ph=p.getMeasuredHeight();
    int w=getMeasuredWidth();
    int h=getMeasuredHeight();
    t=(ph - h) / 2;
    l=(pw - w) / 2;
    r+=l;
    b+=t;
  }
  super.layout(l,t,r,b);
}
