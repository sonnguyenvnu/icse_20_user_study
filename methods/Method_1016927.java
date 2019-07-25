/** 
 * Applies the method argument to each value in a non-binary vector.  The method should both accept a Double as an argument and return a Double.
 * @throws IllegalArgumentException If the method argument has an inappropriate signature.
 * @throws UnsupportedOperationException If vector is binary 
 * @throws IllegalAccessException If the method is inaccessible
 * @throws Throwable If the method throws an exception it is relayed
 */
public final void map(Method f) throws IllegalAccessException, Throwable {
  if (values == null) {
    throw new UnsupportedOperationException("Binary values may not be altered via map");
  }
  if (f.getParameterTypes().length != 1 || f.getParameterTypes()[0] != Double.class || f.getReturnType() != Double.class) {
    throw new IllegalArgumentException("Method signature must be \"Double f (Double x)\"");
  }
  try {
    for (int i=0; i < values.length; i++) {
      Object[] args=new Object[]{Double.valueOf(values[i])};
      values[i]=((Double)f.invoke(null,args)).doubleValue();
    }
  }
 catch (  InvocationTargetException e) {
    throw e.getTargetException();
  }
}
