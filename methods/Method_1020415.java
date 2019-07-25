/** 
 * Returns a  {@link Resource} that describes a k8s container.
 * @param hostname the hostname of the host.
 * @param name the name of the host.
 * @param id the unique host id (instance id in Cloud).
 * @param type the type of the host (machine type).
 * @return a {@link Resource} that describes a k8s container.
 * @since 0.20
 */
public static Resource create(String hostname,String name,String id,String type){
  Map<String,String> labels=new LinkedHashMap<String,String>();
  labels.put(HOSTNAME_KEY,checkNotNull(hostname,"hostname"));
  labels.put(NAME_KEY,checkNotNull(name,"name"));
  labels.put(ID_KEY,checkNotNull(id,"id"));
  labels.put(TYPE_KEY,checkNotNull(type,"type"));
  return Resource.create(TYPE,labels);
}
