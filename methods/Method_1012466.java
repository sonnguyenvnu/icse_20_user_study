public void execute(){
  setState(!on);
  if (extcmd != null) {
    extcmd.execute();
    CircuitElm.sim.repaint();
  }
}
