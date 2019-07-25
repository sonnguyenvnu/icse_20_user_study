/** 
 * Launch Ark Container when run tests
 */
public static void launch(Class testClass){
  if (arkContainer == null) {
synchronized (LOCK) {
      if (arkContainer == null) {
        Object container=SofaArkBootstrap.prepareContainerForTest(testClass);
        wrapping(container);
        arkContainer=container;
      }
    }
  }
  ClassLoaderUtils.pushContextClassLoader(DelegateArkContainer.getTestClassLoader());
}
