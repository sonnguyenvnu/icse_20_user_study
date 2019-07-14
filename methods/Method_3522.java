public static Vertex convert(String word,int type){
  String name=word;
  Nature nature=Nature.n;
  int dValue=1;
switch (type) {
case CharType.CT_CHINESE:
    break;
case CharType.CT_NUM:
case CharType.CT_INDEX:
case CharType.CT_CNUM:
  nature=Nature.m;
word=Predefine.TAG_NUMBER;
break;
case CharType.CT_DELIMITER:
nature=Nature.w;
break;
case CharType.CT_LETTER:
nature=Nature.nx;
word=Predefine.TAG_CLUSTER;
break;
case CharType.CT_SINGLE:
nature=Nature.nx;
word=Predefine.TAG_CLUSTER;
break;
default :
break;
}
return new Vertex(word,name,new CoreDictionary.Attribute(nature,dValue));
}
