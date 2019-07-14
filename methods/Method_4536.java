/** 
 * Peeks the AMR header from the beginning of the input, and consumes it if it exists.
 * @param input The {@link ExtractorInput} from which data should be peeked/read.
 * @return Whether the AMR header has been read.
 */
private boolean readAmrHeader(ExtractorInput input) throws IOException, InterruptedException {
  if (peekAmrSignature(input,amrSignatureNb)) {
    isWideBand=false;
    input.skipFully(amrSignatureNb.length);
    return true;
  }
 else   if (peekAmrSignature(input,amrSignatureWb)) {
    isWideBand=true;
    input.skipFully(amrSignatureWb.length);
    return true;
  }
  return false;
}
