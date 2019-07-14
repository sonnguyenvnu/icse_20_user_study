/** 
 * Construct an instance of a Renderer based on report format name.
 * @param reportFormat The report format name.
 * @param properties Initialization properties for the corresponding Renderer.
 * @return A Renderer instance.
 */
public static Renderer createRenderer(String reportFormat,Properties properties){
  Class<? extends Renderer> rendererClass=getRendererClass(reportFormat);
  Constructor<? extends Renderer> constructor=getRendererConstructor(rendererClass);
  Renderer renderer;
  try {
    if (constructor.getParameterTypes().length > 0) {
      LOG.warning("The renderer uses a deprecated mechanism to use the properties. Please define the needed properties with this.definePropertyDescriptor(..).");
      renderer=constructor.newInstance(properties);
    }
 else {
      renderer=constructor.newInstance();
      for (      PropertyDescriptor<?> prop : renderer.getPropertyDescriptors()) {
        String value=properties.getProperty(prop.name());
        if (value != null) {
          @SuppressWarnings("unchecked") PropertyDescriptor<Object> prop2=(PropertyDescriptor<Object>)prop;
          Object valueFrom=prop2.valueFrom(value);
          renderer.setProperty(prop2,valueFrom);
        }
      }
    }
  }
 catch (  InstantiationException|IllegalAccessException e) {
    throw new IllegalArgumentException("Unable to construct report renderer class: " + e.getLocalizedMessage(),e);
  }
catch (  InvocationTargetException e) {
    throw new IllegalArgumentException("Unable to construct report renderer class: " + e.getTargetException().getLocalizedMessage(),e);
  }
  if (REPORT_FORMAT_TO_RENDERER.containsKey(reportFormat) && !reportFormat.equals(renderer.getName())) {
    if (LOG.isLoggable(Level.WARNING)) {
      LOG.warning("Report format '" + reportFormat + "' is deprecated, and has been replaced with '" + renderer.getName() + "'. Future versions of PMD will remove support for this deprecated Report format usage.");
    }
  }
  return renderer;
}
