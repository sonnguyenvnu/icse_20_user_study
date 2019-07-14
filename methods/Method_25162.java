/** 
 * Returns the value of the leaf of  {@code exprPath}, if it is determined to be a constant (always evaluates to the same numeric value), and null otherwise. Note that returning null does not necessarily mean the expression is *not* a constant.
 */
@Nullable public static Number numberValue(TreePath exprPath,Context context){
  Constant val=DataFlow.expressionDataflow(exprPath,context,CONSTANT_PROPAGATION);
  if (val == null || !val.isConstant()) {
    return null;
  }
  return val.getValue();
}
