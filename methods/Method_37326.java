/** 
 * Registers default set of converters.
 */
public void registerDefaults(){
  register(String.class,new StringConverter());
  register(String[].class,new StringArrayConverter(this));
  IntegerConverter integerConverter=new IntegerConverter();
  register(Integer.class,integerConverter);
  register(int.class,integerConverter);
  register(MutableInteger.class,new MutableIntegerConverter(this));
  ShortConverter shortConverter=new ShortConverter();
  register(Short.class,shortConverter);
  register(short.class,shortConverter);
  register(MutableShort.class,new MutableShortConverter(this));
  LongConverter longConverter=new LongConverter();
  register(Long.class,longConverter);
  register(long.class,longConverter);
  register(MutableLong.class,new MutableLongConverter(this));
  ByteConverter byteConverter=new ByteConverter();
  register(Byte.class,byteConverter);
  register(byte.class,byteConverter);
  register(MutableByte.class,new MutableByteConverter(this));
  FloatConverter floatConverter=new FloatConverter();
  register(Float.class,floatConverter);
  register(float.class,floatConverter);
  register(MutableFloat.class,new MutableFloatConverter(this));
  DoubleConverter doubleConverter=new DoubleConverter();
  register(Double.class,doubleConverter);
  register(double.class,doubleConverter);
  register(MutableDouble.class,new MutableDoubleConverter(this));
  BooleanConverter booleanConverter=new BooleanConverter();
  register(Boolean.class,booleanConverter);
  register(boolean.class,booleanConverter);
  CharacterConverter characterConverter=new CharacterConverter();
  register(Character.class,characterConverter);
  register(char.class,characterConverter);
  register(byte[].class,new ByteArrayConverter(this));
  register(short[].class,new ShortArrayConverter(this));
  register(int[].class,new IntegerArrayConverter(this));
  register(long[].class,new LongArrayConverter(this));
  register(float[].class,new FloatArrayConverter(this));
  register(double[].class,new DoubleArrayConverter(this));
  register(boolean[].class,new BooleanArrayConverter(this));
  register(char[].class,new CharacterArrayConverter(this));
  register(Integer[].class,new ArrayConverter<Integer>(this,Integer.class){
    @Override protected Integer[] createArray(    final int length){
      return new Integer[length];
    }
  }
);
  register(Long[].class,new ArrayConverter<Long>(this,Long.class){
    @Override protected Long[] createArray(    final int length){
      return new Long[length];
    }
  }
);
  register(Byte[].class,new ArrayConverter<Byte>(this,Byte.class){
    @Override protected Byte[] createArray(    final int length){
      return new Byte[length];
    }
  }
);
  register(Short[].class,new ArrayConverter<Short>(this,Short.class){
    @Override protected Short[] createArray(    final int length){
      return new Short[length];
    }
  }
);
  register(Float[].class,new ArrayConverter<Float>(this,Float.class){
    @Override protected Float[] createArray(    final int length){
      return new Float[length];
    }
  }
);
  register(Double[].class,new ArrayConverter<Double>(this,Double.class){
    @Override protected Double[] createArray(    final int length){
      return new Double[length];
    }
  }
);
  register(Boolean[].class,new ArrayConverter<Boolean>(this,Boolean.class){
    @Override protected Boolean[] createArray(    final int length){
      return new Boolean[length];
    }
  }
);
  register(Character[].class,new ArrayConverter<Character>(this,Character.class){
    @Override protected Character[] createArray(    final int length){
      return new Character[length];
    }
  }
);
  register(MutableInteger[].class,new ArrayConverter<>(this,MutableInteger.class));
  register(MutableLong[].class,new ArrayConverter<>(this,MutableLong.class));
  register(MutableByte[].class,new ArrayConverter<>(this,MutableByte.class));
  register(MutableShort[].class,new ArrayConverter<>(this,MutableShort.class));
  register(MutableFloat[].class,new ArrayConverter<>(this,MutableFloat.class));
  register(MutableDouble[].class,new ArrayConverter<>(this,MutableDouble.class));
  register(BigDecimal.class,new BigDecimalConverter());
  register(BigInteger.class,new BigIntegerConverter());
  register(BigDecimal[].class,new ArrayConverter<>(this,BigDecimal.class));
  register(BigInteger[].class,new ArrayConverter<>(this,BigInteger.class));
  register(java.util.Date.class,new DateConverter());
  register(java.sql.Date.class,new SqlDateConverter());
  register(Time.class,new SqlTimeConverter());
  register(Timestamp.class,new SqlTimestampConverter());
  register(Calendar.class,new CalendarConverter());
  register(LocalDateTime.class,new LocalDateTimeConverter());
  register(LocalDate.class,new LocalDateConverter());
  register(LocalTime.class,new LocalTimeConverter());
  register(File.class,new FileConverter());
  register(FileUpload.class,new FileUploadConverter());
  register(Class.class,new ClassConverter());
  register(Class[].class,new ClassArrayConverter(this));
  register(URI.class,new URIConverter());
  register(URL.class,new URLConverter());
  register(Locale.class,new LocaleConverter());
  register(TimeZone.class,new TimeZoneConverter());
  register(UUID.class,new UUIDConverter());
}
