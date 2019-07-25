/** 
 * Returns the result of calling all of the FormatterSteps. The input must have unix line endings, and the output is guaranteed to also have unix line endings.
 */
public String compute(String unix,File file){
  Objects.requireNonNull(unix,"unix");
  Objects.requireNonNull(file,"file");
  for (  FormatterStep step : steps) {
    try {
      String formatted=step.format(unix,file);
      if (formatted == null) {
      }
 else {
        unix=LineEnding.toUnix(formatted);
      }
    }
 catch (    Throwable e) {
      String relativePath=rootDir.relativize(file.toPath()).toString();
      exceptionPolicy.handleError(e,step,relativePath);
    }
  }
  return unix;
}
