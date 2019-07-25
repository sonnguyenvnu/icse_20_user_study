/** 
 * Builds the  {@link TransformInput}.
 * @return a new {@link TransformInput} with the specified jar and directories.
 */
TransformInput build(){
  return new TransformInput(){
    @Override public Collection<JarInput> getJarInputs(){
      return jarInputs;
    }
    @Override public Collection<DirectoryInput> getDirectoryInputs(){
      return directoryInputs;
    }
  }
;
}
