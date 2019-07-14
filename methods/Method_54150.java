/** 
 * Retrieve a material property with a specific key from the material.
 * @param pMat     Pointer to the input material. May not be {@code NULL}
 * @param pKey     Key to search for. One of the AI_MATKEY_XXX constants.
 * @param mPropOut Pointer to receive a pointer to a valid {@link AIMaterialProperty} structure or {@code NULL} if the key has not been found.
 * @return Return_xxx values.
 */
@NativeType("aiReturn") public static int aiGetMaterialProperty(@NativeType("struct aiMaterial const *") AIMaterial pMat,@NativeType("char const *") CharSequence pKey,@NativeType("struct aiMaterialProperty const **") PointerBuffer mPropOut){
  return aiGetMaterialProperty(pMat,pKey,0,0,mPropOut);
}
