/** 
 * Adds a CONSTANT_Module_info to the constant pool of this symbol table. Does nothing if the constant pool already contains a similar item.
 * @param moduleName a fully qualified name (using dots) of a module.
 * @return a new or already existing Symbol with the given value.
 */
Symbol addConstantModule(final String moduleName){
  return addConstantUtf8Reference(Symbol.CONSTANT_MODULE_TAG,moduleName);
}
