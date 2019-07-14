/** 
 * Optional call to open the underlying  {@link DataSource}. <p> Calling this method does nothing if the  {@link DataSource} is already open. Calling thismethod is optional, since the read and skip methods will automatically open the underlying {@link DataSource} if it's not open already.
 * @throws IOException If an error occurs opening the {@link DataSource}.
 */
public void open() throws IOException {
  checkOpened();
}
