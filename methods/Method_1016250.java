/** 
 * {@inheritDoc}
 */
@Override public Object unmarshal(final HierarchicalStreamReader reader,final UnmarshallingContext context){
  String nearClass=reader.getAttribute(NEAR_CLASS_ATTRIBUTE);
  if (nearClass == null) {
    nearClass=(String)context.get(ComponentStyleConverter.PAINTER_CLASS_ATTRIBUTE);
  }
  if (nearClass == null) {
    return null;
  }
 else {
    final Class nearRealClass=ReflectUtils.getClassSafely(nearClass);
    if (nearRealClass == null) {
      return null;
    }
 else {
      final String iconPath=reader.getValue();
      try {
        return new NinePatchIcon(nearRealClass.getResource(iconPath));
      }
 catch (      final Throwable e) {
        Log.error(this,"Unable to read 9-patch icon near class \"" + nearClass + "\": " + iconPath,e);
        return null;
      }
    }
  }
}
