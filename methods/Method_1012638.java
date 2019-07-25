/** 
 * Returns a "deep-clone" of this instance where mutable objects are copied.
 * @return The new {@link FFmpegProgramInfo}.
 */
@Override @Nonnull public FFmpegProgramInfo copy(){
  return new FFmpegProgramInfo(programName,defaultType,originalDefaultType,executablesInfo);
}
