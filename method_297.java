private List<PngChunk> _XXXXX_(final List<PngChunk> chunks,final ChunkType type){
  final List<PngChunk> result=new ArrayList<>();
  for (  final PngChunk chunk : chunks) {
    if (chunk.chunkType == type.value) {
      result.add(chunk);
    }
  }
  return result;
}