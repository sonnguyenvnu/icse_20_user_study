/** 
 * Close the streams so that the temporary files can be deleted. <p/> File.deleteOnExit() cannot be used because the stdout and stderr files are inside a folder, and have to be deleted before the folder itself is deleted, which can't be guaranteed when using the deleteOnExit() method.
 */
static public void shutdown(){
  System.setOut(systemOut);
  System.setErr(systemErr);
  cleanup(consoleOut);
  cleanup(consoleErr);
  cleanup(stdoutFile);
  cleanup(stderrFile);
}
