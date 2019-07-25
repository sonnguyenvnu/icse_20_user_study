public static MappingConfig_AbstractRef load(ModelInputStream stream) throws IOException {
  int cl=stream.readInt();
  if (cl == PERSISTENCE_ID) {
    return new MappingConfig_AbstractRef();
  }
 else   if (cl == MappingConfig_RefAllGlobal.PERSISTENCE_ID) {
    return new MappingConfig_RefAllGlobal();
  }
 else   if (cl == MappingConfig_RefAllLocal.PERSISTENCE_ID) {
    return new MappingConfig_RefAllLocal();
  }
 else   if (cl == MappingConfig_SimpleRef.PERSISTENCE_ID) {
    MappingConfig_SimpleRef simpleRef=new MappingConfig_SimpleRef();
    simpleRef.setModelUID(stream.readString());
    simpleRef.setNodeID(stream.readString());
    return simpleRef;
  }
 else   if (cl == MappingConfig_ExternalRef.PERSISTENCE_ID) {
    MappingConfig_ExternalRef result=new MappingConfig_ExternalRef();
    result.setGenerator(stream.readModuleReference());
    result.setMappingConfig(load(stream));
    return result;
  }
 else   if (cl == MappingConfig_RefSet.PERSISTENCE_ID) {
    MappingConfig_RefSet result=new MappingConfig_RefSet();
    for (int size=stream.readInt(); size > 0; size--) {
      result.getMappingConfigs().add(load(stream));
    }
    return result;
  }
 else   if (cl == 0x70) {
    return null;
  }
  throw new IOException("bad stream");
}
