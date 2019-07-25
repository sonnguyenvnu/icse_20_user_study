/** 
 * Binds the given function across each element of this array with a final join.
 * @param f The function to apply to each element of this array.
 * @return A new array after performing the map, then final join.
 */
@SuppressWarnings("unchecked") public <B>Array<B> bind(final F<A,Array<B>> f){
  List<Array<B>> x=List.nil();
  int len=0;
  for (int i=a.length - 1; i >= 0; i--) {
    final Array<B> bs=f.f((A)a[i]);
    len=len + bs.length();
    x=x.cons(bs);
  }
  final Object[] bs=new Object[len];
  x.foreach(new F<Array<B>,Unit>(){
    public Unit f(    final Array<B> x){
      arraycopy(x.a,0,bs,i,x.a.length);
      i=i + x.a.length;
      return unit();
    }
  }
);
  return new Array<>(bs);
}
