/** 
 * Retrieve a UVTransform value with a specific key from a material.
 * @param pMat  Pointer to the input material. May not be {@code NULL}
 * @param pKey  Key to search for. One of the AI_MATKEY_XXX constants.
 * @param type  Specifies the type of the texture to be retrieved. One of:<br><table><tr><td>{@link #aiTextureType_NONE TextureType_NONE}</td><td> {@link #aiTextureType_DIFFUSE TextureType_DIFFUSE}</td><td> {@link #aiTextureType_SPECULAR TextureType_SPECULAR}</td><td> {@link #aiTextureType_AMBIENT TextureType_AMBIENT}</td></tr><tr><td> {@link #aiTextureType_EMISSIVE TextureType_EMISSIVE}</td><td> {@link #aiTextureType_HEIGHT TextureType_HEIGHT}</td><td> {@link #aiTextureType_NORMALS TextureType_NORMALS}</td><td> {@link #aiTextureType_SHININESS TextureType_SHININESS}</td></tr><tr><td> {@link #aiTextureType_OPACITY TextureType_OPACITY}</td><td> {@link #aiTextureType_DISPLACEMENT TextureType_DISPLACEMENT}</td><td> {@link #aiTextureType_LIGHTMAP TextureType_LIGHTMAP}</td><td> {@link #aiTextureType_REFLECTION TextureType_REFLECTION}</td></tr><tr><td> {@link #aiTextureType_UNKNOWN TextureType_UNKNOWN}</td></tr></table>
 * @param index Index of the texture to be retrieved.
 * @param pOut  Pointer to a {@link AIUVTransform} to receive the result.
 * @return Specifies whether the key has been found. If not, the output struct remains unmodified.
 */
@NativeType("aiReturn") public static int aiGetMaterialUVTransform(@NativeType("struct aiMaterial const *") AIMaterial pMat,@NativeType("char const *") CharSequence pKey,@NativeType("unsigned int") int type,@NativeType("unsigned int") int index,@NativeType("struct aiUVTransform *") AIUVTransform pOut){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(pKey,true);
    long pKeyEncoded=stack.getPointerAddress();
    return naiGetMaterialUVTransform(pMat.address(),pKeyEncoded,type,index,pOut.address());
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
