/** 
 * Replace named instructions with the standard implementations.
 * @param method Method instance.
 * @param updateLocals Populate local variable table.
 */
static int clean(MethodNode method,boolean updateLocals){
  Type type=Type.getType(method.desc);
  Type[] argTypes=type.getArgumentTypes();
  Map<String,Var> varMap=new LinkedHashMap<>();
  int nextIndex=0, highest=0;
  boolean isStatic=AccessFlag.isStatic(method.access);
  Map<Integer,Integer> paramToVarIndex=new HashMap<>();
  int paramIndex=1;
  int argSize=isStatic ? 0 : 1;
  for (  Type typeArg : argTypes) {
    paramToVarIndex.put(paramIndex,argSize);
switch (typeArg.getSort()) {
case Type.DOUBLE:
case Type.LONG:
      argSize+=2;
    break;
default :
  argSize++;
}
paramIndex++;
}
nextIndex+=argSize;
Set<NamedVarRefInsn> replaceSet=new HashSet<>();
for (AbstractInsnNode ain : method.instructions.toArray()) {
if (ain instanceof NamedVarRefInsn) {
NamedVarRefInsn named=(NamedVarRefInsn)ain;
replaceSet.add(named);
String key=named.getVarName();
if (!varMap.containsKey(key)) varMap.put(key,new Var(key,ain));
}
}
for (Map.Entry<String,Var> e : varMap.entrySet()) {
String key=e.getKey();
Var v=e.getValue();
if (Parse.isInt(key)) {
int index=Integer.parseInt(key);
if (index > highest) {
  highest=index;
}
v.index=index;
if (nextIndex <= index) {
  nextIndex=index + 1;
  if (v.isWide) {
    nextIndex++;
  }
}
continue;
}
 else if (key.matches("p\\d\\w*")) {
Matcher m=new Pattern("p({INDEX}\\d+)\\w*").matcher(key);
m.find();
int index=Integer.parseInt(m.group("INDEX"));
v.key=v.key.substring(1 + String.valueOf(index).length());
if (index - 1 >= argTypes.length) {
  throw new AssemblyResolveError(v.ain,String.format("Specified parameter does not exist, " + "given %d but maximum is %d.",index,argSize));
}
v.desc=argTypes[index - 1].getDescriptor();
if (paramToVarIndex.containsKey(index)) {
  index=paramToVarIndex.get(index);
}
 else {
  String expected=Arrays.toString(paramToVarIndex.values().toArray(new Integer[0]));
  throw new AssemblyResolveError(v.ain,String.format("Specified parameter does not exist, " + "given %d but allowed values are: %s",index,expected));
}
if (index > highest) {
  highest=index;
}
if (isStatic) index-=1;
v.index=index;
continue;
}
 else if (key.equals("this")) {
v.index=0;
continue;
}
int index=nextIndex;
if (index > highest) {
highest=index;
}
v.index=index;
nextIndex++;
if (v.isWide) {
nextIndex++;
}
}
method.localVariables=new ArrayList<>();
LabelNode start=new LabelNode();
LabelNode end=new LabelNode();
if (updateLocals) {
method.instructions.insert(start);
method.instructions.add(end);
}
Set<Integer> used=new HashSet<>();
for (NamedVarRefInsn nvri : replaceSet) {
AbstractInsnNode index=(AbstractInsnNode)nvri;
Var v=varMap.get(nvri.getVarName());
if (updateLocals && !used.contains(v.index)) {
updateLocal(method,v,start,end);
used.add(v.index);
}
method.instructions.set(index,nvri.clone(v));
}
return nextIndex;
}
