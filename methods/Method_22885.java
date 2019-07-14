protected JMenu buildEditMenu(){
  JMenu menu=new JMenu(Language.text("menu.edit"));
  JMenuItem item;
  undoItem=Toolkit.newJMenuItem(undoAction=new UndoAction(),'Z');
  menu.add(undoItem);
  redoItem=new JMenuItem(redoAction=new RedoAction());
  redoItem.setAccelerator(Toolkit.getKeyStrokeExt("menu.edit.redo"));
  menu.add(redoItem);
  menu.addSeparator();
  item=Toolkit.newJMenuItem(cutAction=new CutAction(),'X');
  editMenuUpdatable.add(cutAction);
  menu.add(item);
  item=Toolkit.newJMenuItem(copyAction=new CopyAction(),'C');
  editMenuUpdatable.add(copyAction);
  menu.add(item);
  item=Toolkit.newJMenuItemShift(copyAsHtmlAction=new CopyAsHtmlAction(),'C');
  editMenuUpdatable.add(copyAsHtmlAction);
  menu.add(item);
  item=Toolkit.newJMenuItem(pasteAction=new PasteAction(),'V');
  editMenuUpdatable.add(pasteAction);
  menu.add(item);
  item=Toolkit.newJMenuItem(Language.text("menu.edit.select_all"),'A');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      textarea.selectAll();
    }
  }
);
  menu.add(item);
  menu.addSeparator();
  item=Toolkit.newJMenuItem(Language.text("menu.edit.auto_format"),'T');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handleAutoFormat();
    }
  }
);
  menu.add(item);
  item=Toolkit.newJMenuItemExt("menu.edit.comment_uncomment");
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handleCommentUncomment();
    }
  }
);
  menu.add(item);
  item=Toolkit.newJMenuItemExt("menu.edit.increase_indent");
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handleIndentOutdent(true);
    }
  }
);
  menu.add(item);
  item=Toolkit.newJMenuItemExt("menu.edit.decrease_indent");
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handleIndentOutdent(false);
    }
  }
);
  menu.add(item);
  menu.addSeparator();
  item=Toolkit.newJMenuItem(Language.text("menu.edit.find"),'F');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      if (find == null) {
        find=new FindReplace(Editor.this);
      }
      String selection=getSelectedText();
      if (selection != null && selection.length() != 0 && !selection.contains("\n")) {
        find.setFindText(selection);
      }
      find.setVisible(true);
    }
  }
);
  menu.add(item);
  UpdatableAction action;
  item=Toolkit.newJMenuItem(action=new FindNextAction(),'G');
  editMenuUpdatable.add(action);
  menu.add(item);
  item=Toolkit.newJMenuItemShift(action=new FindPreviousAction(),'G');
  editMenuUpdatable.add(action);
  menu.add(item);
  item=Toolkit.newJMenuItem(action=new SelectionForFindAction(),'E');
  editMenuUpdatable.add(action);
  menu.add(item);
  menu.addMenuListener(new MenuListener(){
    @Override public void menuCanceled(    MenuEvent e){
      for (      UpdatableAction a : editMenuUpdatable) {
        a.setEnabled(true);
      }
    }
    @Override public void menuDeselected(    MenuEvent e){
      for (      UpdatableAction a : editMenuUpdatable) {
        a.setEnabled(true);
      }
    }
    @Override public void menuSelected(    MenuEvent e){
      for (      UpdatableAction a : editMenuUpdatable) {
        a.updateState();
      }
    }
  }
);
  return menu;
}
