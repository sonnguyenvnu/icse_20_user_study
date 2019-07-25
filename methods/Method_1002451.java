/** 
 * Wrap a Data object by using the provided concrete  {@link DataTemplate} class to create an instance of the classthat wraps the Data object.
 * @param object provides the Data object to wrap.
 * @param schema provides the {@link DataSchema} of the concrete {@link DataTemplate} class.
 * @param wrapperClass provides the concrete {@link DataTemplate} class.
 * @param < T > provides the concrete {@link DataTemplate} type.
 * @return an instance of the provided {@link DataTemplate} class that wraps the Data object.
 * @throws TemplateOutputCastException if the provided object cannot be wrapped by the provided concrete{@link DataTemplate} class.
 */
public static <T extends DataTemplate<?>>T wrap(Object object,DataSchema schema,Class<T> wrapperClass) throws TemplateOutputCastException {
  try {
    return templateConstructor(wrapperClass,schema).newInstance(object);
  }
 catch (  IllegalArgumentException e) {
    throw new TemplateOutputCastException("Could not create new instance of " + wrapperClass.getName() + " with argument " + object,e);
  }
catch (  InstantiationException e) {
    throw new TemplateOutputCastException("Could not create new instance of " + wrapperClass.getName() + ": cannot initialize an abstract class",e);
  }
catch (  IllegalAccessException e) {
    throw new TemplateOutputCastException("Could not create new instance of " + wrapperClass.getName() + ": access control denies access to constructor",e);
  }
catch (  InvocationTargetException e) {
    throw new TemplateOutputCastException("Could not create new instance of " + wrapperClass.getName() + ": constructor throws an exception",e);
  }
catch (  ClassCastException e) {
    throw new TemplateOutputCastException("Could not create new instance of " + wrapperClass.getName() + " with argument " + object,e);
  }
}
