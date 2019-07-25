/** 
 * Reverse this array in constant stack space.
 * @return A new array that is the reverse of this one.
 */
public Array<A> reverse(){
  final Object[] x=new Object[a.length];
  for (int i=0; i < a.length; i++) {
    x[a.length - 1 - i]=a[i];
  }
  return new Array<>(x);
}
