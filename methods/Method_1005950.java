/** 
 * Parses a volume from specification to a  {@link VolumesFrom}.
 * @param serialized the specification, e.g. <code>container:ro</code>
 * @return a {@link VolumesFrom} matching the specification
 * @throws IllegalArgumentException if the specification cannot be parsed
 */
public static VolumesFrom parse(String serialized){
  try {
    String[] parts=serialized.split(":");
switch (parts.length) {
case 1:
{
        return new VolumesFrom(parts[0]);
      }
case 2:
{
      return new VolumesFrom(parts[0],AccessMode.valueOf(parts[1]));
    }
default :
{
    throw new IllegalArgumentException();
  }
}
}
 catch (Exception e) {
throw new IllegalArgumentException("Error parsing Bind '" + serialized + "'");
}
}
