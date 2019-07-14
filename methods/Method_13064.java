/** 
 * Called internally by installGtkPopupBugWorkaround to fix the thickness of a GTK style field by setting it to a minimum value of 1.
 * @param style The GTK style object.
 * @param fieldName The field name.
 * @throws Exception When reflection fails.
 */
private static void fixGtkThickness(Object style,String fieldName) throws Exception {
  Field field=style.getClass().getDeclaredField(fieldName);
  boolean accessible=field.isAccessible();
  field.setAccessible(true);
  field.setInt(style,Math.max(1,field.getInt(style)));
  field.setAccessible(accessible);
}
