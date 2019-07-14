private void setExitOnEscWhenEnabled(JComponent mainComponent){
  Action escapeAction=new AbstractAction(){
    @Override public void actionPerformed(    ActionEvent e){
      if (luytenPrefs.isExitByEscEnabled()) {
        quit();
      }
    }
  }
;
  KeyStroke escapeKeyStroke=KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0,false);
  mainComponent.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(escapeKeyStroke,"ESCAPE");
  mainComponent.getActionMap().put("ESCAPE",escapeAction);
}
