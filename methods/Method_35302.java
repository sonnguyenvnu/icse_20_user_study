/** 
 * The animation framework has an optimization for <code>Properties</code> of type <code>float</code> but it was only made public in API24, so wrap the impl in our own type and conditionally create the appropriate type, delegating the implementation.
 */
public static <T>Property<T,Float> createFloatProperty(final FloatProp<T> impl){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    return new FloatProperty<T>(impl.name){
      @Override public Float get(      T object){
        return impl.get(object);
      }
      @Override public void setValue(      T object,      float value){
        impl.set(object,value);
      }
    }
;
  }
 else {
    return new Property<T,Float>(Float.class,impl.name){
      @Override public Float get(      T object){
        return impl.get(object);
      }
      @Override public void set(      T object,      Float value){
        impl.set(object,value);
      }
    }
;
  }
}
