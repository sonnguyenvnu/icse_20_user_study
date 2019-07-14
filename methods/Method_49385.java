private static CompressionOptions compressionOptions(final Configuration configuration){
  if (!configuration.get(CF_COMPRESSION)) {
    return noCompression();
  }
  return Match(configuration.get(CF_COMPRESSION_TYPE)).of(Case($("LZ4Compressor"),lz4()),Case($("SnappyCompressor"),snappy()),Case($("DeflateCompressor"),deflate())).withChunkLengthInKb(configuration.get(CF_COMPRESSION_BLOCK_SIZE));
}
