@Override public void setValue(String value){
  innerList.clear();
  innerList.add(new Word(value,label));
}
