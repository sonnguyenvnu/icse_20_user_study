public static void save(MappingConfig_AbstractRef ref,ModelOutputStream stream) throws IOException {
  if (ref == null) {
    stream.writeInt(0x70);
  }
 else   if (ref instanceof MappingConfig_RefAllGlobal) {
    stream.writeInt(MappingConfig_RefAllGlobal.PERSISTENCE_ID);
  }
 else   if (ref instanceof MappingConfig_RefAllLocal) {
    stream.writeInt(MappingConfig_RefAllLocal.PERSISTENCE_ID);
  }
 else   if (ref instanceof MappingConfig_SimpleRef) {
    MappingConfig_SimpleRef simpleRef=(MappingConfig_SimpleRef)ref;
    stream.writeInt(MappingConfig_SimpleRef.PERSISTENCE_ID);
    stream.writeString(simpleRef.getModelUID());
    stream.writeString(simpleRef.getNodeID());
  }
 else   if (ref instanceof MappingConfig_ExternalRef) {
    stream.writeInt(MappingConfig_ExternalRef.PERSISTENCE_ID);
    MappingConfig_ExternalRef extRef=(MappingConfig_ExternalRef)ref;
    stream.writeModuleReference(extRef.getGenerator());
    save(extRef.getMappingConfig(),stream);
  }
 else   if (ref instanceof MappingConfig_RefSet) {
    stream.writeInt(MappingConfig_RefSet.PERSISTENCE_ID);
    List<MappingConfig_AbstractRef> list=((MappingConfig_RefSet)ref).getMappingConfigs();
    stream.writeInt(list.size());
    for (    MappingConfig_AbstractRef inner : list) {
      save(inner,stream);
    }
  }
 else {
    stream.writeInt(PERSISTENCE_ID);
  }
}
