public void rebuildMenu(){
  if (menu != null) {
    menu.removeAll();
  }
 else {
    menu=new JMenu();
    popup=menu.getPopupMenu();
    add(popup);
    popup.setLightWeightPopupEnabled(true);
  }
  JMenuItem item;
  final JRootPane rootPane=editor.getRootPane();
  InputMap inputMap=rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
  ActionMap actionMap=rootPane.getActionMap();
  Action action;
  String mapKey;
  KeyStroke keyStroke;
  item=Toolkit.newJMenuItemShift(Language.text("editor.header.new_tab"),KeyEvent.VK_N);
  action=new AbstractAction(){
    @Override public void actionPerformed(    ActionEvent e){
      editor.getSketch().handleNewCode();
    }
  }
;
  mapKey="editor.header.new_tab";
  keyStroke=KeyStroke.getKeyStroke(KeyEvent.VK_N,Toolkit.SHORTCUT_SHIFT_KEY_MASK);
  inputMap.put(keyStroke,mapKey);
  actionMap.put(mapKey,action);
  item.addActionListener(action);
  menu.add(item);
  item=new JMenuItem(Language.text("editor.header.rename"));
  action=new AbstractAction(){
    @Override public void actionPerformed(    ActionEvent e){
      editor.getSketch().handleRenameCode();
    }
  }
;
  item.addActionListener(action);
  menu.add(item);
  item=Toolkit.newJMenuItemShift(Language.text("editor.header.delete"),KeyEvent.VK_D);
  action=new AbstractAction(){
    @Override public void actionPerformed(    ActionEvent e){
      Sketch sketch=editor.getSketch();
      if (!Platform.isMacOS() && editor.base.getEditors().size() == 1 && sketch.getCurrentCodeIndex() == 0) {
        Messages.showWarning(Language.text("editor.header.delete.warning.title"),Language.text("editor.header.delete.warning.text"));
      }
 else {
        editor.getSketch().handleDeleteCode();
      }
    }
  }
;
  mapKey="editor.header.delete";
  keyStroke=KeyStroke.getKeyStroke(KeyEvent.VK_D,Toolkit.SHORTCUT_SHIFT_KEY_MASK);
  inputMap.put(keyStroke,mapKey);
  actionMap.put(mapKey,action);
  item.addActionListener(action);
  menu.add(item);
  menu.addSeparator();
  mapKey="editor.header.previous_tab";
  item=Toolkit.newJMenuItemExt(mapKey);
  action=new AbstractAction(){
    @Override public void actionPerformed(    ActionEvent e){
      editor.getSketch().handlePrevCode();
    }
  }
;
  keyStroke=item.getAccelerator();
  inputMap.put(keyStroke,mapKey);
  actionMap.put(mapKey,action);
  item.addActionListener(action);
  menu.add(item);
  mapKey="editor.header.next_tab";
  item=Toolkit.newJMenuItemExt(mapKey);
  action=new AbstractAction(){
    @Override public void actionPerformed(    ActionEvent e){
      editor.getSketch().handleNextCode();
    }
  }
;
  keyStroke=item.getAccelerator();
  inputMap.put(keyStroke,mapKey);
  actionMap.put(mapKey,action);
  item.addActionListener(action);
  menu.add(item);
  Sketch sketch=editor.getSketch();
  if (sketch != null) {
    menu.addSeparator();
    ActionListener jumpListener=new ActionListener(){
      public void actionPerformed(      ActionEvent e){
        editor.getSketch().setCurrentCode(e.getActionCommand());
      }
    }
;
    for (    SketchCode code : sketch.getCode()) {
      item=new JMenuItem(code.getPrettyName());
      item.addActionListener(jumpListener);
      menu.add(item);
    }
  }
  Toolkit.setMenuMnemonics(menu);
}
