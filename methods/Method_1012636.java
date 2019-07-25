/** 
 * Returns a "deep-clone" of this instance where mutable objects are copied.
 * @return The new {@link ExternalProgramInfo}.
 */
@Nonnull public ExternalProgramInfo copy(){
  return new ExternalProgramInfo(programName,defaultType,originalDefaultType,executablesInfo);
}
