/** 
 * Called internally by installGtkPopupBugWorkaround. Returns a specific GTK style object.
 * @param styleFactory The GTK style factory.
 * @param component The target component of the style.
 * @param regionName The name of the target region of the style.
 * @return The GTK style.
 * @throws Exception When reflection fails.
 */
private static Object getGtkStyle(Object styleFactory,JComponent component,String regionName) throws Exception {
  Class<?> regionClass=Class.forName("javax.swing.plaf.synth.Region");
  Field field=regionClass.getField(regionName);
  Object region=field.get(regionClass);
  Class<?> styleFactoryClass=styleFactory.getClass();
  Method method=styleFactoryClass.getMethod("getStyle",JComponent.class,regionClass);
  boolean accessible=method.isAccessible();
  method.setAccessible(true);
  Object style=method.invoke(styleFactory,component,region);
  method.setAccessible(accessible);
  return style;
}
