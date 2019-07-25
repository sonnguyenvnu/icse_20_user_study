private void resize(TableColumnBase column,double delta){
  try {
    Class<?> clazz=Class.forName("javafx.scene.control.TableUtil");
    Method constrainedResize=clazz.getDeclaredMethod("resize",TableColumnBase.class,double.class);
    constrainedResize.setAccessible(true);
    constrainedResize.invoke(null,column,delta);
  }
 catch (  NoSuchMethodException|IllegalAccessException|InvocationTargetException|ClassNotFoundException e) {
    LOGGER.error("Could not invoke resize in TableUtil",e);
  }
}
