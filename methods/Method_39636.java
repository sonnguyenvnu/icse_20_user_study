/** 
 * Returns the size in bytes of this symbol table's BootstrapMethods attribute. Also adds the attribute name in the constant pool.
 * @return the size in bytes of this symbol table's BootstrapMethods attribute.
 */
int computeBootstrapMethodsSize(){
  if (bootstrapMethods != null) {
    addConstantUtf8(Constants.BOOTSTRAP_METHODS);
    return 8 + bootstrapMethods.length;
  }
 else {
    return 0;
  }
}
