@PostConstruct public void init(){
  setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
  setOpaque(false);
  add(Box.createHorizontalGlue());
  add(connectButton);
}
