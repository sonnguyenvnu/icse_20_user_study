private byte[] instrument(final byte[] source){
  final long classId=CRC64.classId(source);
  final ClassReader reader=InstrSupport.classReaderFor(source);
  final ClassWriter writer=new ClassWriter(reader,0){
    @Override protected String getCommonSuperClass(    final String type1,    final String type2){
      throw new IllegalStateException();
    }
  }
;
  final IProbeArrayStrategy strategy=ProbeArrayStrategyFactory.createFor(classId,reader,accessorGenerator);
  final int version=InstrSupport.getMajorVersion(reader);
  final ClassVisitor visitor=new ClassProbesAdapter(new ClassInstrumenter(strategy,writer),InstrSupport.needsFrames(version));
  reader.accept(visitor,ClassReader.EXPAND_FRAMES);
  return writer.toByteArray();
}
