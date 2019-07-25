/** 
 * Returns the inheritance hierarchy of this class.
 * @return The inheritance hierarchy of this class.
 */
public List<Class<? super T>> inheritance(){
  return unfold((  java.lang.Class<? super T> c2) -> {
    if (c2 == null)     return none();
 else {
      final P2<java.lang.Class<? super T>,java.lang.Class<? super T>> p=new P2<java.lang.Class<? super T>,java.lang.Class<? super T>>(){
        public java.lang.Class<? super T> _1(){
          return c2;
        }
        @SuppressWarnings("unchecked") public java.lang.Class<? super T> _2(){
          return c2.getSuperclass();
        }
      }
;
      return some(p);
    }
  }
,c).map(Class::clas);
}
