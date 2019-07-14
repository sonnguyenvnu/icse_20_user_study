/** 
 * create ComponentName by component type and class type
 * @param clazz
 * @param type
 */
public static ComponentName createComponentName(ComponentType type,Class<?> clazz){
  return new ComponentName(type,mergeComponentName(clazz,null));
}
