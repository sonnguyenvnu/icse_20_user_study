private String decompress(byte[] headerRawData,List<Short> pointers) throws IllegalRawDataException {
  if (pointer == null) {
    return name;
  }
 else {
    if (pointers.contains(pointer)) {
      StringBuilder sb=new StringBuilder(200);
      sb.append("Circular reference detected. data: ").append(ByteArrays.toHexString(headerRawData," ")).append(", offset: ").append(pointer).append(", name: ").append(name);
      throw new IllegalRawDataException(sb.toString());
    }
    pointers.add(pointer);
    StringBuilder sb=new StringBuilder();
    sb.append(name).append(".").append(new DnsDomainName(headerRawData,pointer,headerRawData.length - pointer).decompress(headerRawData,pointers));
    return sb.toString();
  }
}
