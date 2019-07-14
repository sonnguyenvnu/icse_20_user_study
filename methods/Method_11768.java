/** 
 * Use to assume that an operation completes normally.  If  {@code e} is non-null, the test will halt and be ignored.For example: <pre> \@Test public void parseDataFile() { DataFile file; try { file = DataFile.open("sampledata.txt"); } catch (IOException e) { // stop test and ignore if data can't be opened assumeNoException(e); } // ... } </pre>
 * @param e if non-null, the offending exception
 */
public static void assumeNoException(Throwable e){
  assumeThat(e,nullValue());
}
