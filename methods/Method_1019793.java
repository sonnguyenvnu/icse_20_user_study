public void initialize(Object[] object) throws com.sun.star.uno.Exception {
  if (object.length > 0) {
    m_xFrame=(com.sun.star.frame.XFrame)UnoRuntime.queryInterface(com.sun.star.frame.XFrame.class,object[0]);
  }
}
