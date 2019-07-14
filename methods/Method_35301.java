/** 
 * The animation framework has an optimization for <code>Properties</code> of type <code>int</code> but it was only made public in API24, so wrap the impl in our own type and conditionally create the appropriate type, delegating the implementation.
 */
public static <T>Property<T,Integer> createIntProperty(final IntProp<T> impl){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    return new IntProperty<T>(impl.name){
      @Override public Integer get(      T object){
        return impl.get(object);
      }
      @Override public void setValue(      T object,      int value){
        impl.set(object,value);
      }
    }
;
  }
 else {
    return new Property<T,Integer>(Integer.class,impl.name){
      @Override public Integer get(      T object){
        return impl.get(object);
      }
      @Override public void set(      T object,      Integer value){
        impl.set(object,value);
      }
    }
;
  }
}
