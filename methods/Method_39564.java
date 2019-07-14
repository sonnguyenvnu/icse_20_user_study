/** 
 * Adds a module reference to the constant pool of the class being build. Does nothing if the constant pool already contains a similar item. <i>This method is intended for  {@link Attribute}sub classes, and is normally not needed by class generators or adapters.</i>
 * @param moduleName name of the module.
 * @return the index of a new or already existing module reference item.
 */
public int newModule(final String moduleName){
  return symbolTable.addConstantModule(moduleName).index;
}
