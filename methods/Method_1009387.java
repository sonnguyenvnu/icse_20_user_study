public static CheckItemBuilt check(String name,boolean checked){
  RadioMenuItem item=new RadioMenuItem(){
    @Override public boolean equals(    Object o){
      if (this == o)       return true;
      if (o == null || getClass() != o.getClass())       return false;
      MenuItem i=(MenuItem)o;
      return !(getText() != null ? !getText().equals(i.getText()) : i.getText() != null);
    }
    @Override public int hashCode(){
      return getText() != null ? getText().hashCode() : 0;
    }
  }
;
  item.setMnemonicParsing(false);
  item.setSelected(checked);
  item.setText(name);
  final CheckItemBuilt checkItemBuilt=new CheckItemBuilt(item);
  return checkItemBuilt;
}
