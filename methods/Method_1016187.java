public static void install(Component component,int resizeSide){
  WindowResizeAdapter wra=new WindowResizeAdapter(resizeSide);
  component.addMouseListener(wra);
  component.addMouseMotionListener(wra);
}
