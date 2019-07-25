@Override public void dispose(){
  if (myOptions != null) {
    myOptions.getStateObject().addReturnsOnImplement=myAddReturn.isSelected();
    myOptions.getStateObject().removeAttributes=myRemoveAttributes.isSelected();
  }
  super.dispose();
}
