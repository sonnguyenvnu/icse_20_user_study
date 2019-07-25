@Override public Expression optimize(Session session){
  boolean allConst=info.deterministic;
  for (int i=0; i < args.length; i++) {
    Expression e=args[i];
    if (e == null) {
      continue;
    }
    e=e.optimize(session);
    args[i]=e;
    if (!e.isConstant()) {
      allConst=false;
    }
  }
  TypeInfo typeInfo;
  Expression p0=args.length < 1 ? null : args[0];
switch (info.type) {
case DATEADD:
{
      typeInfo=TypeInfo.TYPE_TIMESTAMP;
      if (p0.isConstant()) {
        Expression p2=args[2];
switch (p2.getType().getValueType()) {
case Value.TIME:
          typeInfo=TypeInfo.TYPE_TIME;
        break;
case Value.DATE:
{
        int field=DateTimeFunctions.getDatePart(p0.getValue(session).getString());
switch (field) {
case HOUR:
case MINUTE:
case SECOND:
case EPOCH:
case MILLISECOND:
case MICROSECOND:
case NANOSECOND:
          break;
default :
        type=TypeInfo.TYPE_DATE;
    }
    break;
  }
case Value.TIMESTAMP_TZ:
type=TypeInfo.TYPE_TIMESTAMP_TZ;
}
}
break;
}
case EXTRACT:
{
if (p0.isConstant() && DateTimeFunctions.getDatePart(p0.getValue(session).getString()) == Function.EPOCH) {
typeInfo=TypeInfo.getTypeInfo(Value.DECIMAL,ValueLong.PRECISION + ValueTimestamp.MAXIMUM_SCALE,ValueTimestamp.MAXIMUM_SCALE,null);
}
 else {
typeInfo=TypeInfo.TYPE_INT;
}
break;
}
case DATE_TRUNC:
typeInfo=args[1].getType();
if (typeInfo.getValueType() != Value.TIMESTAMP_TZ) {
typeInfo=TypeInfo.TYPE_TIMESTAMP;
}
break;
case IFNULL:
case NULLIF:
case COALESCE:
case LEAST:
case GREATEST:
{
typeInfo=TypeInfo.TYPE_UNKNOWN;
for (Expression e : args) {
if (!e.isNullConstant()) {
TypeInfo type=e.getType();
int valueType=type.getValueType();
if (valueType != Value.UNKNOWN && valueType != Value.NULL) {
typeInfo=Value.getHigherType(typeInfo,type);
}
}
}
if (typeInfo.getValueType() == Value.UNKNOWN) {
typeInfo=TypeInfo.TYPE_STRING;
}
break;
}
case CASE:
case DECODE:
{
typeInfo=TypeInfo.TYPE_UNKNOWN;
for (int i=2, len=args.length; i < len; i+=2) {
Expression then=args[i];
if (!then.isNullConstant()) {
TypeInfo type=then.getType();
int valueType=type.getValueType();
if (valueType != Value.UNKNOWN && valueType != Value.NULL) {
typeInfo=Value.getHigherType(typeInfo,type);
}
}
}
if (args.length % 2 == 0) {
Expression elsePart=args[args.length - 1];
if (!elsePart.isNullConstant()) {
TypeInfo type=elsePart.getType();
int valueType=type.getValueType();
if (valueType != Value.UNKNOWN && valueType != Value.NULL) {
typeInfo=Value.getHigherType(typeInfo,type);
}
}
}
if (typeInfo.getValueType() == Value.UNKNOWN) {
typeInfo=TypeInfo.TYPE_STRING;
}
break;
}
case CASEWHEN:
typeInfo=Value.getHigherType(args[1].getType(),args[2].getType());
break;
case NVL2:
{
TypeInfo t1=args[1].getType(), t2=args[2].getType();
switch (t1.getValueType()) {
case Value.STRING:
case Value.CLOB:
case Value.STRING_FIXED:
case Value.STRING_IGNORECASE:
typeInfo=TypeInfo.getTypeInfo(t1.getValueType(),-1,0,null);
break;
default :
typeInfo=Value.getHigherType(t1,t2);
break;
}
break;
}
case CAST:
case CONVERT:
case TRUNCATE_VALUE:
if (type != null) {
typeInfo=type;
}
 else {
typeInfo=TypeInfo.TYPE_UNKNOWN;
}
break;
case CEILING:
case FLOOR:
case ROUND:
switch (p0.getType().getValueType()) {
case Value.DOUBLE:
typeInfo=TypeInfo.TYPE_DOUBLE;
break;
case Value.FLOAT:
typeInfo=TypeInfo.TYPE_FLOAT;
break;
default :
typeInfo=getRoundNumericType(session);
}
break;
case TRUNCATE:
switch (p0.getType().getValueType()) {
case Value.DOUBLE:
typeInfo=TypeInfo.TYPE_DOUBLE;
break;
case Value.FLOAT:
typeInfo=TypeInfo.TYPE_FLOAT;
break;
case Value.STRING:
case Value.DATE:
case Value.TIMESTAMP:
if (args.length > 1) {
throw DbException.get(ErrorCode.INVALID_PARAMETER_COUNT_2,info.name,"1");
}
typeInfo=TypeInfo.getTypeInfo(Value.TIMESTAMP,-1,0,null);
break;
case Value.TIMESTAMP_TZ:
if (args.length > 1) {
throw DbException.get(ErrorCode.INVALID_PARAMETER_COUNT_2,info.name,"1");
}
typeInfo=TypeInfo.getTypeInfo(Value.TIMESTAMP_TZ,-1,0,null);
break;
default :
typeInfo=getRoundNumericType(session);
}
break;
case ABS:
{
TypeInfo type=p0.getType();
typeInfo=type;
if (typeInfo.getValueType() == Value.NULL) {
typeInfo=TypeInfo.TYPE_INT;
}
break;
}
case SET:
typeInfo=args[1].getType();
if (!(p0 instanceof Variable)) {
throw DbException.get(ErrorCode.CAN_ONLY_ASSIGN_TO_VARIABLE_1,p0.getSQL(false));
}
break;
case FILE_READ:
{
if (args.length == 1) {
typeInfo=TypeInfo.getTypeInfo(Value.BLOB,Integer.MAX_VALUE,0,null);
}
 else {
typeInfo=TypeInfo.getTypeInfo(Value.CLOB,Integer.MAX_VALUE,0,null);
}
break;
}
case SUBSTRING:
{
TypeInfo argType=args[0].getType();
long p=argType.getPrecision();
if (args[1].isConstant()) {
p-=args[1].getValue(session).getLong() - 1;
}
if (args.length == 3 && args[2].isConstant()) {
p=Math.min(p,args[2].getValue(session).getLong());
}
p=Math.max(0,p);
typeInfo=TypeInfo.getTypeInfo(DataType.isBinaryStringType(argType.getValueType()) ? Value.BYTES : Value.STRING,p,0,null);
break;
}
case ENCRYPT:
case DECRYPT:
typeInfo=TypeInfo.getTypeInfo(info.returnDataType,args[2].getType().getPrecision(),0,null);
break;
case COMPRESS:
typeInfo=TypeInfo.getTypeInfo(info.returnDataType,args[0].getType().getPrecision(),0,null);
break;
case CHAR:
typeInfo=TypeInfo.getTypeInfo(info.returnDataType,1,0,null);
break;
case CONCAT:
{
long p=0;
for (Expression e : args) {
TypeInfo type=e.getType();
p+=type.getPrecision();
if (p < 0) {
p=Long.MAX_VALUE;
}
}
typeInfo=TypeInfo.getTypeInfo(info.returnDataType,p,0,null);
break;
}
case HEXTORAW:
typeInfo=TypeInfo.getTypeInfo(info.returnDataType,(args[0].getType().getPrecision() + 3) / 4,0,null);
break;
case LCASE:
case LTRIM:
case RIGHT:
case RTRIM:
case UCASE:
case LOWER:
case UPPER:
case TRIM:
case STRINGDECODE:
case UTF8TOSTRING:
typeInfo=TypeInfo.getTypeInfo(info.returnDataType,args[0].getType().getPrecision(),0,null);
break;
case RAWTOHEX:
typeInfo=TypeInfo.getTypeInfo(info.returnDataType,args[0].getType().getPrecision() * 4,0,null);
break;
case SOUNDEX:
typeInfo=TypeInfo.getTypeInfo(info.returnDataType,4,0,null);
break;
case DAY_NAME:
case MONTH_NAME:
typeInfo=TypeInfo.getTypeInfo(info.returnDataType,20,0,null);
break;
default :
typeInfo=TypeInfo.getTypeInfo(info.returnDataType,-1,-1,null);
}
type=typeInfo;
if (allConst) {
Value v=getValue(session);
if (info.type == CAST || info.type == CONVERT) {
if (v == ValueNull.INSTANCE) {
return TypedValueExpression.get(ValueNull.INSTANCE,type);
}
DataType dt=DataType.getDataType(type.getValueType());
TypeInfo vt=v.getType();
if (dt.supportsPrecision && type.getPrecision() != vt.getPrecision() || dt.supportsScale && type.getScale() != vt.getScale()) {
return TypedValueExpression.get(v,type);
}
}
return ValueExpression.get(v);
}
return this;
}
