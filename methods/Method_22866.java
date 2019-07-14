protected char[] getCharacters(){
  if (defaultCharsButton.isSelected()) {
    return PFont.CHARSET;
  }
  char[] charset=new char[65536];
  if (allCharsButton.isSelected()) {
    for (int i=0; i < 0xFFFF; i++) {
      charset[i]=(char)i;
    }
  }
 else {
    DefaultListModel model=(DefaultListModel)charsetList.getModel();
    int index=0;
    for (int i=0; i < BLOCKS.length; i++) {
      if (((JCheckBox)model.get(i)).isSelected()) {
        for (int j=blockStart[i]; j <= blockStop[i]; j++) {
          charset[index++]=(char)j;
        }
      }
    }
    charset=PApplet.subset(charset,0,index);
  }
  return charset;
}
