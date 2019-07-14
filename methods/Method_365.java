public void visitLabel(final Label label){
  label.resolve(this,code.length,code.data);
}
