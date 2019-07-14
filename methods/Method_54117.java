/** 
 * Create a modifiable copy of a scene. <p>This is useful to import files via Assimp, change their topology and export them again. Since the scene returned by the various importer functions is const, a modifiable copy is needed.</p>
 * @param pIn Valid scene to be copied
 */
@Nullable @NativeType("void") public static AIScene aiCopyScene(@NativeType("struct aiScene const *") AIScene pIn){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    PointerBuffer pOut=stack.callocPointer(1);
    naiCopyScene(pIn.address(),memAddress(pOut));
    return AIScene.createSafe(pOut.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
