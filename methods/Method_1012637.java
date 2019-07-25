/** 
 * @return A new {@link FFmpegExecutableInfoBuilder} initialized with thevalues of this  {@link FFmpegExecutableInfo}. When done modifying, convert the  {@link FFmpegExecutableInfoBuilder} into a new{@link FFmpegExecutableInfo} instance with{@link FFmpegExecutableInfoBuilder#build()}.
 */
@Override public FFmpegExecutableInfoBuilder modify(){
  return new FFmpegExecutableInfoBuilder(this);
}
