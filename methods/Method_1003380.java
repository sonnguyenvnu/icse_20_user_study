/** 
 * Build the jar file without downloading any files over the network. If the required files are missing, they are listed, and the jar file is not built.
 */
@Description(summary="Build H2 jar avoiding downloads (list missing files).") public void offline(){
  downloadOrVerify(true);
  if (filesMissing) {
    println("Required files are missing");
  }
 else {
    jar();
  }
}
