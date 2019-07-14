public int init(int n,int m){
  final int msize=5;
  final int size=n;
  iflag_=0;
  w_=new double[size * (2 * msize + 1) + 2 * msize];
  Arrays.fill(w_,0.0);
  diag_=new double[size];
  v_=new double[size];
  return 0;
}
