/** 
 * Attempts to determine the image format of the specified data and evaluates its format compliance.   This method returns a FormatCompliance object which includes information about the data's compliance to a specific format.
 * @param bytes a valid array of bytes containing image data.
 * @return if successful, a valid FormatCompliance object.
 * @throws ImageReadException in the event of unreadable data.
 * @throws IOException in the event of an unrecoverable I/O condition.
 */
public static FormatCompliance _XXXXX_(final byte[] bytes) throws ImageReadException, IOException {
  return _XXXXX_(new ByteSourceArray(bytes));
}