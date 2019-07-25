@Override public void dispose(){
  if (myOptions != null) {
    myOptions.addOverrideAnnotation=myInsertOverride.isSelected();
    myOptions.addReturnsOnImplement=myAddReturn.isSelected();
    myOptions.removeAttributes=myRemoveAttributes.isSelected();
  }
  super.dispose();
}
