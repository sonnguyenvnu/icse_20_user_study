/** 
 * <p>Writes a property set to a document in a POI filesystem directory.</p>
 * @param dir The directory in the POI filesystem to write the document to.
 * @param name The document's name. If there is already a document with thesame name in the directory the latter will be overwritten.
 * @throws WritingNotSupportedException
 * @throws IOException
 */
public void write(final DirectoryEntry dir,final String name) throws WritingNotSupportedException, IOException {
  try {
    final Entry e=dir.getEntry(name);
    e.delete();
  }
 catch (  FileNotFoundException ex) {
  }
  dir.createDocument(name,toInputStream());
}
