/** 
 * @since 4.13
 */
protected void invokeMethod(FrameworkMethod method) throws Throwable {
  method.invokeExplosively(target);
}
