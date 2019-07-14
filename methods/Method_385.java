/** 
 * @since 1.2.57
 */
public String toString(SerializerFeature... features){
  SerializeWriter out=new SerializeWriter(null,JSON.DEFAULT_GENERATE_FEATURE,features);
  try {
    new JSONSerializer(out).write(this);
    return out.toString();
  }
  finally {
    out.close();
  }
}
