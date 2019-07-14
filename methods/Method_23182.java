@Override public void placePresent(int stopColor){
  setFullFrame();
  canvas.setBounds((screenRect.width - sketchWidth) / 2,(screenRect.height - sketchHeight) / 2,sketchWidth,sketchHeight);
  if (stopColor != 0) {
    Label label=new Label("stop");
    label.setForeground(new Color(stopColor,false));
    label.addMouseListener(new MouseAdapter(){
      @Override public void mousePressed(      java.awt.event.MouseEvent e){
        sketch.exit();
      }
    }
);
    frame.add(label);
    Dimension labelSize=label.getPreferredSize();
    labelSize=new Dimension(100,labelSize.height);
    label.setSize(labelSize);
    label.setLocation(20,screenRect.height - labelSize.height - 20);
  }
}
