private void buildThemesMenu(JMenu themesMenu){
  themesMenu.removeAll();
  themesGroup=new ButtonGroup();
  JRadioButtonMenuItem a=new JRadioButtonMenuItem(new ThemeAction("Default","default.xml"));
  a.setSelected("default.xml".equals(luytenPrefs.getThemeXml()));
  themesGroup.add(a);
  themesMenu.add(a);
  a=new JRadioButtonMenuItem(new ThemeAction("Default-Alt","default-alt.xml"));
  a.setSelected("default-alt.xml".equals(luytenPrefs.getThemeXml()));
  themesGroup.add(a);
  themesMenu.add(a);
  a=new JRadioButtonMenuItem(new ThemeAction("Dark","dark.xml"));
  a.setSelected("dark.xml".equals(luytenPrefs.getThemeXml()));
  themesGroup.add(a);
  themesMenu.add(a);
  a=new JRadioButtonMenuItem(new ThemeAction("Eclipse","eclipse.xml"));
  a.setSelected("eclipse.xml".equals(luytenPrefs.getThemeXml()));
  themesGroup.add(a);
  themesMenu.add(a);
  a=new JRadioButtonMenuItem(new ThemeAction("Visual Studio","vs.xml"));
  a.setSelected("vs.xml".equals(luytenPrefs.getThemeXml()));
  themesGroup.add(a);
  themesMenu.add(a);
  a=new JRadioButtonMenuItem(new ThemeAction("IntelliJ","idea.xml"));
  a.setSelected("idea.xml".equals(luytenPrefs.getThemeXml()));
  themesGroup.add(a);
  themesMenu.add(a);
}
