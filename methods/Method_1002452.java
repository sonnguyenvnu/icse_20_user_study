/** 
 * Wrap a Data object by using the provided constructor to create a  {@link DataTemplate} to wrap the Data object.
 * @param object provides the Data object to wrap.
 * @param constructor provides the constructor previously obtained from{@link DataTemplateUtil#templateConstructor(Class,DataSchema)}.
 * @param < T > provides the concrete {@link DataTemplate} type.
 * @return an instance of {@link DataTemplate} that wraps the Data object.
 * @throws TemplateOutputCastException if a {@link DataTemplate} cannot be created to wrap the provided Data object usingthe provided constructor.
 */
public static <T extends DataTemplate<?>>T wrap(Object object,Constructor<T> constructor){
  try {
    return constructor.newInstance(object);
  }
 catch (  IllegalArgumentException e) {
    throw new TemplateOutputCastException("Could not create new instance of " + constructor.getClass().getName() + " using constructor " + constructor.getName() + " with argument " + object,e);
  }
catch (  InstantiationException e) {
    throw new TemplateOutputCastException("Could not create new instance of " + constructor.getClass().getName() + " using constructor " + constructor.getName() + ": cannot initialize an abstract class",e);
  }
catch (  IllegalAccessException e) {
    throw new TemplateOutputCastException("Could not create new instance of " + constructor.getClass().getName() + " using constructor " + constructor.getName() + ": access control denies access to constructor",e);
  }
catch (  InvocationTargetException e) {
    throw new TemplateOutputCastException("Could not create new instance of " + constructor.getClass().getName() + " using constructor " + constructor.getName() + ": constructor throws an exception",e);
  }
catch (  ClassCastException e) {
    throw new TemplateOutputCastException("Could not create new instance of " + constructor.getClass().getName() + " using constructor " + constructor.getName() + " with argument " + object,e);
  }
}
