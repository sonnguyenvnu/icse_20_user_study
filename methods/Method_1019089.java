/** 
 * @param method
 * @param onSave
 * @return FxAssembler for editing instructions.
 */
public static FxAssembler insns(ClassNode owner,MethodNode method,Consumer<MethodNode> onSave){
  FxAssembler fx=new FxAssembler(method,onSave);
  fx.asm.setHostType(owner.name);
  fx.doParse=true;
  fx.verify=false;
  fx.chkVerify.setSelected(false);
  return fx;
}
