package org.nutz.dao.jdbc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.nutz.castor.Castors;
import org.nutz.dao.DaoException;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.impl.entity.field.NutMappingField;
import org.nutz.dao.impl.jdbc.BlobValueAdaptor;
import org.nutz.dao.impl.jdbc.ClobValueAdaptor;
import org.nutz.dao.util.Daos;
import org.nutz.filepool.FilePool;
import org.nutz.json.Json;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.Streams;
import org.nutz.lang.Strings;
import org.nutz.lang.meta.Email;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.trans.Trans;

/**
 * �??供一些与 JDBC 有关的帮助函数
 *
 * @author zozoh(zozohtnt@gmail.com) TODO �?�并到NutConfig
 */
public abstract class Jdbcs {

    private static final Log log = Logs.get();

    private static final JdbcExpertConfigFile conf;

    public static Map<String, ValueAdaptor> customValueAdaptorMap = new ConcurrentHashMap<String, ValueAdaptor>();

    /*
     * 根�?��?置文件获�?� experts 的列表
     */
    static {
        try {

            // 看看有没有用户自定义的映射文件
            File f = Files.findFile("nutz_jdbc_experts.js");// TODO �?�?��?置??
            // 如果没有则使用默认的映射文件
            if (null == f) {
                conf = Json.fromJson(JdbcExpertConfigFile.class, Streams.utf8r(Jdbcs.class.getResourceAsStream("nutz_jdbc_experts.js"))).init();
            } else
                conf = Json.fromJson(JdbcExpertConfigFile.class,Streams.fileInr("nutz_jdbc_experts.js")).init();

            for (String key : conf.getExperts().keySet()) {
                // 检查一下正则表达�?是�?�正确
                // 在conf类中自行检查
                // Pattern.compile(key,Pattern.DOTALL &
                // Pattern.CASE_INSENSITIVE);
                // 检查一下是�?��?�以生�? Expert 的实例
                conf.getExpert(key);// TODO 值得商讨
            }
        }
        catch (Exception e) {
            throw Lang.wrapThrow(e);
        }
        if (log.isDebugEnabled())
            log.debug("Jdbcs init complete");
    }

    /**
     * 针对一个数�?��?，返回其专属的 JdbcExpert
     *
     * @param ds
     *            数�?��?
     * @return 该数�?�库的特殊驱动�?装类
     *
     * @see org.nutz.dao.jdbc.Jdbcs#getExpert(String, String)
     */
    public static JdbcExpert getExpert(DataSource ds) {
        log.info("Get Connection from DataSource for JdbcExpert, if you lock at here, check your database server and configure");
        Connection conn = null;
        try {
            conn = Trans.getConnectionAuto(ds);
            DatabaseMetaData meta = conn.getMetaData();
            String pnm = meta.getDatabaseProductName();
            String ver = meta.getDatabaseProductVersion();
            return getExpert(pnm, ver);
        }
        catch (Throwable e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            Trans.closeConnectionAuto(conn);
        }
    }

    /**
     * 根�?�数�?�库的产�?�??称，获�?�其专属的 Expert
     * <p>
     * 映射的规则存放在 JSON 文件 "nutz_jdbc_experts.js" 中，你�?�以通过建立这个文件修改 Nutz 的默认映射规则
     * <p>
     * 比如下�?�的文件，将支�?两�?数�?�库
     *
     * <pre>
     * {
     *   experts : {
     *     "postgresql.*" : "org.nutz.dao.impl.jdbc.psql.PostgresqlExpert",
     *     "mysql.*" :  "org.nutz.dao.impl.jdbc.mysql.MysqlExpert"
     *   },
     *   config : {
     *     "temp-home" : "~/.nutz/tmp/dao/",
     *     "temp-max" : 2000
     *   }
     * }
     * </pre>
     *
     * 本函数传入的两个�?�数将会被:
     *
     * <pre>
     * String.format(&quot;%s::NUTZ_JDBC::%s&quot;, productName, version);
     * </pre>
     *
     * 并被你声明的正则表达�?(expert 段下的键值)�?次匹�?，如果匹�?上了，就会用相应的类当作驱动�?装类
     *
     * @param productName
     *            数�?�库产�?�??称
     * @param version
     *            数�?�库版本�?�
     *
     * @return 该数�?�库的特殊驱动�?装类
     *
     * @see java.sql.Connection#getMetaData()
     * @see java.sql.DatabaseMetaData#getDatabaseProductName()
     */
    public static JdbcExpert getExpert(String productName, String version) {
        String dbName = String.format("%s::NUTZ_JDBC::%s", productName, version).toLowerCase();

        JdbcExpert re = conf.matchExpert(dbName);

        if (null == re) {
        	log.warnf("unknow database type '%s %s', fallback to MySql 5", productName, version);
        	re = conf.matchExpert("mysql 5");
        }

        return re;
    }

    public static ValueAdaptor getAdaptorBy(Object obj) {
        if (null == obj)
            return Adaptor.asNull;
        return getAdaptor(Mirror.me(obj));
    }

    /**
     * 注册一个自定义ValueAdaptor,若adaptor为null,则�?�消注册
     * @param className 类�??
     * @param adaptor 值适�?器实例,若为null,则�?�消注册
     * @return 原有的值适�?器
     */
    public static ValueAdaptor register(String className, ValueAdaptor adaptor) {
        if (adaptor == null)
            return customValueAdaptorMap.remove(className);
        return customValueAdaptorMap.put(className, adaptor);
    }

    public static ValueAdaptor getAdaptor(Mirror<?> mirror) {
        ValueAdaptor custom = customValueAdaptorMap.get(mirror.getType().getName());
        if (custom != null)
            return custom;
        // String and char
        if (mirror.isStringLike())
            return Jdbcs.Adaptor.asString;
        // Int
        if (mirror.isInt())
            return Jdbcs.Adaptor.asInteger;
        // Boolean
        if (mirror.isBoolean())
            return Jdbcs.Adaptor.asBoolean;
        // Long
        if (mirror.isLong())
            return Jdbcs.Adaptor.asLong;
        // Enum
        if (mirror.isEnum())
            return Jdbcs.Adaptor.asEnumChar;
        // Char
        if (mirror.isChar())
            return Jdbcs.Adaptor.asChar;
        // Timestamp
        if (mirror.isOf(Timestamp.class))
            return Jdbcs.Adaptor.asTimestamp;
        // Byte
        if (mirror.isByte())
            return Jdbcs.Adaptor.asByte;
        // Short
        if (mirror.isShort())
            return Jdbcs.Adaptor.asShort;
        // Float
        if (mirror.isFloat())
            return Jdbcs.Adaptor.asFloat;
        // Double
        if (mirror.isDouble())
            return Jdbcs.Adaptor.asDouble;
        // BigDecimal
        if (mirror.isOf(BigDecimal.class))
            return Jdbcs.Adaptor.asBigDecimal;
        // java.sql.Date
        if (mirror.isOf(java.sql.Date.class))
            return Jdbcs.Adaptor.asSqlDate;
        // java.sql.Time
        if (mirror.isOf(java.sql.Time.class))
            return Jdbcs.Adaptor.asSqlTime;
        // Calendar
        if (mirror.isOf(Calendar.class))
            return Jdbcs.Adaptor.asCalendar;
        // java.util.Date
        if (mirror.isOf(java.util.Date.class))
            return Jdbcs.Adaptor.asDate;
        // Blob
        if (mirror.isOf(Blob.class))
            return new BlobValueAdaptor(conf.getPool());
        // Clob
        if (mirror.isOf(Clob.class))
            return new ClobValueAdaptor(conf.getPool());
        // byte[]
        if (mirror.getType().isArray() && mirror.getType().getComponentType() == byte.class) {
            return Jdbcs.Adaptor.asBytes;
        }
        // inputstream
        if (mirror.isOf(InputStream.class))
            return Jdbcs.Adaptor.asBinaryStream;
        if (mirror.isOf(Reader.class))
            return Jdbcs.Adaptor.asReader;
        if (mirror.isLocalDateLike())
            return Adaptor.asLocalDate;
        if (mirror.isLocalDateTimeLike())
            return Jdbcs.Adaptor.asLocalDateTime;
        // 默认情况
        return Jdbcs.Adaptor.asString;
    }

    public static class Adaptor {
        /**
         * 空值适�?器
         */
        public static final ValueAdaptor asNull = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                return null;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                stat.setNull(i, Types.NULL);
            }

        };

        /**
         * 字符串适�?器
         */
        public static final ValueAdaptor asString = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                return rs.getString(colName);
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setString(i, null);
                } else {
                    stat.setString(i, Castors.me().castToString(obj));
                }
            }
        };

        /**
         * 字符适�?器
         */
        public static final ValueAdaptor asChar = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                String re = Strings.trim(rs.getString(colName));
                if (re == null || re.length() == 0)
                    return null;
                return re;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setString(i, null);
                } else {
                    String s;
                    if (obj instanceof Character) {
                        int c = ((Character) obj).charValue();
                        if (c >= 0 && c <= 32)
                            s = " ";
                        else
                            s = String.valueOf((char) c);
                    } else
                        s = obj.toString();
                    stat.setString(i, s);
                }
            }
        };

        /**
         * 整型适�?器
         */
        public static final ValueAdaptor asInteger = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                int re = rs.getInt(colName);
                return rs.wasNull() ? null : re;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.INTEGER);
                } else {
                    int v;
                    if (obj instanceof Number)
                        v = ((Number) obj).intValue();
                    else
                        v = Castors.me().castTo(obj.toString(), int.class);
                    stat.setInt(i, v);
                }
            }
        };

        /**
         * 大数适�?器
         */
        public static final ValueAdaptor asBigDecimal = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                return rs.getBigDecimal(colName);
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.BIGINT);
                } else {
                    BigDecimal v;
                    if (obj instanceof BigDecimal)
                        v = (BigDecimal) obj;
                    else if (obj instanceof Number)
                        v = BigDecimal.valueOf(((Number) obj).longValue());
                    else
                        v = new BigDecimal(obj.toString());
                    stat.setBigDecimal(i, v);
                }
            }
        };

        /**
         * 布尔适�?器
         * <p>
         * 对 Oracle，Types.BOOLEAN 对于 setNull 是�?工作的 因此 OracleExpert 会用一个新的
         * Adaptor 处�?�自己这�?特殊情况
         */
        public static final ValueAdaptor asBoolean = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                boolean re = rs.getBoolean(colName);
                return rs.wasNull() ? null : re;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.BOOLEAN);
                } else {
                    boolean v;
                    if (obj instanceof Boolean)
                        v = (Boolean) obj;
                    else if (obj instanceof Number)
                        v = ((Number) obj).intValue() > 0;
                    else if (obj instanceof Character)
                        v = Character.toUpperCase((Character) obj) == 'T';
                    else
                        v = Boolean.valueOf(obj.toString());
                    stat.setBoolean(i, v);
                }
            }
        };

        /**
         * 长整适�?器
         */
        public static final ValueAdaptor asLong = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                long re = rs.getLong(colName);
                return rs.wasNull() ? null : re;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.INTEGER);
                } else {
                    long v;
                    if (obj instanceof Number)
                        v = ((Number) obj).longValue();
                    else
                        v = Castors.me().castTo(obj.toString(), long.class);
                    stat.setLong(i, v);
                }
            }
        };

        /**
         * 字节适�?器
         */
        public static final ValueAdaptor asByte = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                byte re = rs.getByte(colName);
                return rs.wasNull() ? null : re;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.TINYINT);
                } else {
                    byte v;
                    if (obj instanceof Number)
                        v = ((Number) obj).byteValue();
                    else
                        v = Castors.me().castTo(obj.toString(), byte.class);
                    stat.setByte(i, v);
                }
            }
        };

        /**
         * 短整型适�?器
         */
        public static final ValueAdaptor asShort = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                short re = rs.getShort(colName);
                return rs.wasNull() ? null : re;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.SMALLINT);
                } else {
                    short v;
                    if (obj instanceof Number)
                        v = ((Number) obj).shortValue();
                    else
                        v = Castors.me().castTo(obj.toString(), short.class);
                    stat.setShort(i, v);
                }
            }
        };

        /**
         * 浮点适�?器
         */
        public static final ValueAdaptor asFloat = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                float re = rs.getFloat(colName);
                return rs.wasNull() ? null : re;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.FLOAT);
                } else {
                    float v;
                    if (obj instanceof Number)
                        v = ((Number) obj).floatValue();
                    else
                        v = Castors.me().castTo(obj.toString(), float.class);
                    stat.setFloat(i, v);
                }
            }
        };

        /**
         * �?�精度浮点适�?器
         */
        public static final ValueAdaptor asDouble = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                double re = rs.getDouble(colName);
                return rs.wasNull() ? null : re;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.DOUBLE);
                } else {
                    double v;
                    if (obj instanceof Number)
                        v = ((Number) obj).doubleValue();
                    else
                        v = Castors.me().castTo(obj.toString(), double.class);
                    stat.setDouble(i, v);
                }
            }
        };

        /**
         * 日历适�?器
         */
        public static final ValueAdaptor asCalendar = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                Timestamp ts = rs.getTimestamp(colName);
                if (null == ts)
                    return null;
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(ts.getTime());
                return c;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.TIMESTAMP);
                } else {
                    Timestamp v;
                    if (obj instanceof Calendar)
                        v = new Timestamp(((Calendar) obj).getTimeInMillis());
                    else
                        v = Castors.me().castTo(obj, Timestamp.class);
                    stat.setTimestamp(i, v);
                }
            }
        };

        /**
         * 时间戳适�?器
         */
        public static final ValueAdaptor asTimestamp = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                return rs.getTimestamp(colName);
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.TIMESTAMP);
                } else {
                    Timestamp v;
                    if (obj instanceof Timestamp)
                        v = (Timestamp) obj;
                    else
                        v = Castors.me().castTo(obj, Timestamp.class);
                    stat.setTimestamp(i, v);
                }
            }
        };

        /**
         * 日期适�?器
         */
        public static final ValueAdaptor asDate = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                Timestamp ts = rs.getTimestamp(colName);
                return null == ts ? null : new java.util.Date(ts.getTime());
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                Timestamp v;
                if (null == obj) {
                    stat.setNull(i, Types.TIMESTAMP);
                } else {
                    if (obj instanceof java.util.Date)
                        v = new Timestamp(((java.util.Date) obj).getTime());
                    else
                        v = Castors.me().castTo(obj, Timestamp.class);
                    stat.setTimestamp(i, v);
                }
            }
        };

        /**
         * Sql 日期适�?器
         */
        public static final ValueAdaptor asSqlDate = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                return rs.getDate(colName);
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.DATE);
                } else {
                    java.sql.Date v;
                    if (obj instanceof java.sql.Date)
                        v = (java.sql.Date) obj;
                    else
                        v = Castors.me().castTo(obj, java.sql.Date.class);
                    stat.setDate(i, v);
                }
            }
        };

        /**
         * Sql 时间适�?器
         */
        public static final ValueAdaptor asSqlTime = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                return rs.getTime(colName);
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                java.sql.Time v;
                if (null == obj) {
                    stat.setNull(i, Types.TIME);
                } else {
                    if (obj instanceof java.sql.Time)
                        v = (java.sql.Time) obj;
                    else
                        v = Castors.me().castTo(obj, java.sql.Time.class);
                    stat.setTime(i, v);
                }
            }
        };

        /**
         * 数字枚举适�?器
         */
        public static final ValueAdaptor asEnumInt = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                int re = rs.getInt(colName);
                return rs.wasNull() ? null : re;
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setNull(i, Types.INTEGER);
                } else {
                    stat.setInt(i, Castors.me().castTo(obj, int.class));
                }
            }
        };

        /**
         * 字符枚举适�?器
         */
        public static final ValueAdaptor asEnumChar = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                return rs.getString(colName);
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                if (null == obj) {
                    stat.setString(i, null);
                } else {
                    String v = obj.toString();
                    stat.setString(i, v);
                }
            }
        };

        /**
         * 默认对象适�?器
         */
        public static final ValueAdaptor asObject = new ValueAdaptor() {
            public Object get(ResultSet rs, String colName) throws SQLException {
                return rs.getObject(colName);
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                stat.setObject(i, obj);
            }
        };

        /**
         * 字节数组适�?器
         */
        public static final ValueAdaptor asBytes = new ValueAdaptor() {

            public Object get(ResultSet rs, String colName) throws SQLException {
                return rs.getBytes(colName);
            }

            public void set(PreparedStatement stat, Object obj, int index) throws SQLException {
                if (null == obj) {
                    stat.setNull(index, Types.BINARY);
                } else {
                    stat.setBytes(index, (byte[]) obj);
                }
            }

        };

        public static final ValueAdaptor asBinaryStream = new ValueAdaptor() {

            public Object get(ResultSet rs, String colName) throws SQLException {
            	InputStream in = rs.getBinaryStream(colName);
            	if (in == null) {
            		return in;
            	}
            	try {
					File f = File.createTempFile("nutzdao_blob", ".tmp");
					Files.write(f, in);
					in.close();
					return new ReadOnceInputStream(f);
				}
				catch (IOException e) {
					throw Lang.wrapThrow(e);
				}
            }

            public void set(PreparedStatement stat, Object obj, int index) throws SQLException {
                if (null == obj) {
                    stat.setNull(index, Types.BINARY);
                } else {

                	if (obj instanceof ByteArrayInputStream) {
                		stat.setBinaryStream(index, (InputStream)obj, ((ByteArrayInputStream)obj).available());
                	} else if (obj instanceof InputStream) {
                		if (obj instanceof ReadOnceInputStream) {
                			if (((ReadOnceInputStream)obj).readed) {
                				throw new DaoException("");
                			}
                		}
                        try {
                            File f = Jdbcs.getFilePool().createFile(".dat");
                            Streams.writeAndClose(new FileOutputStream(f), (InputStream)obj);
                            stat.setBinaryStream(index, new FileInputStream(f), f.length());
                        }
                        catch (FileNotFoundException e) {
                        	try {
                                File f = Jdbcs.getFilePool().createFile(".dat");
                                Streams.writeAndClose(new FileOutputStream(f), (InputStream)obj);
                                stat.setBinaryStream(index, new FileInputStream(f), f.length());
                            }
                            catch (FileNotFoundException e2) {
                            	throw Lang.impossible();
                            }
                        }
                    }
                }
            }
        };

        public static final ValueAdaptor asReader = new ValueAdaptor() {

            public Object get(ResultSet rs, String colName) throws SQLException {
                return rs.getCharacterStream(colName);
            }

            public void set(PreparedStatement stat, Object obj, int index) throws SQLException {
                if (null == obj) {
                    stat.setNull(index, Types.BINARY);
                } else {
                    setCharacterStream(index, obj, stat);
                }
            }
        };
        
        
        public static final ValueAdaptor asLocalDateTime = new ValueAdaptor() {
            
            public Object get(ResultSet rs, String colName) throws SQLException {
                Timestamp ts = rs.getTimestamp(colName);
                return null == ts ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneId.systemDefault());
            }

            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                Timestamp v;
                if (null == obj) {
                    stat.setNull(i, Types.TIMESTAMP);
                } else {
                    v = Timestamp.valueOf((LocalDateTime)obj);
                    stat.setTimestamp(i, v);
                }
            }
        };
    
        public static final ValueAdaptor asLocalDate = new ValueAdaptor() {
        
            public Object get(ResultSet rs, String colName) throws SQLException {
                Timestamp ts = rs.getTimestamp(colName);
                return null == ts ? null : ts.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }
        
            public void set(PreparedStatement stat, Object obj, int i) throws SQLException {
                Timestamp v;
                if (null == obj) {
                    stat.setNull(i, Types.TIMESTAMP);
                } else {
                    v = Timestamp.valueOf(((LocalDate)obj).atStartOfDay(ZoneId.systemDefault()).toLocalDateTime());
                    stat.setTimestamp(i, v);
                }
            }
        };
    }

    /**
     * 根�?�字段现有的信�?�，尽�?�能猜测一下字段的数�?�库类型
     *
     * @param ef
     *            映射字段
     */
    public static void guessEntityFieldColumnType(NutMappingField ef) {
        Mirror<?> mirror = ef.getTypeMirror();

        // 整型
        if (mirror.isInt()) {
            ef.setColumnType(ColType.INT);
            ef.setWidth(8);
        }
        // 字符串
        else if (mirror.isStringLike() || mirror.is(Email.class)) {
            ef.setColumnType(ColType.VARCHAR);
            ef.setWidth(Daos.DEFAULT_VARCHAR_WIDTH);
        }
        // 长整型
        else if (mirror.isLong()) {
            ef.setColumnType(ColType.INT);
            ef.setWidth(16);
        }
        // 枚举
        else if (mirror.isEnum()) {
            ef.setColumnType(ColType.VARCHAR);
            ef.setWidth(20);
        }
        // 时间戳
        else if (mirror.is(Timestamp.class)) {
            ef.setColumnType(ColType.TIMESTAMP);
        }
        // 布尔
        else if (mirror.isBoolean()) {
            ef.setColumnType(ColType.BOOLEAN);
            ef.setWidth(1);
        }
        // 字符
        else if (mirror.isChar()) {
            ef.setColumnType(ColType.CHAR);
            ef.setWidth(4);
        }
        // 日期
        else if (mirror.is(java.sql.Date.class)) {
            ef.setColumnType(ColType.DATE);
        }
        // 时间
        else if (mirror.is(java.sql.Time.class)) {
            ef.setColumnType(ColType.TIME);
        }
        // 日期时间
        else if (mirror.isOf(Calendar.class) || mirror.is(java.util.Date.class) || mirror.isLocalDateTimeLike()) {
            ef.setColumnType(ColType.DATETIME);
        }
        // 大数
        else if (mirror.is(BigDecimal.class)) {
            ef.setColumnType(ColType.INT);
            ef.setWidth(32);
        }
        // 短整型
        else if (mirror.isShort()) {
            ef.setColumnType(ColType.INT);
            ef.setWidth(4);
        }
        // 字节
        else if (mirror.isByte()) {
            ef.setColumnType(ColType.INT);
            ef.setWidth(2);
        }
        // 浮点
        else if (mirror.isFloat()) {
            ef.setColumnType(ColType.FLOAT);
        }
        // �?�精度浮点
        else if (mirror.isDouble()) {
            ef.setColumnType(ColType.FLOAT);
        }
        // 文本�?
        else if (mirror.isOf(Reader.class) || mirror.isOf(Clob.class)) {
            ef.setColumnType(ColType.TEXT);
        }
        // 二进制�?
        else if (mirror.isOf(InputStream.class)
                 || mirror.is(byte[].class)
                 || mirror.isOf(Blob.class)) {
            ef.setColumnType(ColType.BINARY);
        }
        /*
         * 上�?�的都�?是？ 那就当作字符串好了，�??正�?�以 toString
         */
        else {
            if (log.isDebugEnabled()&& ef.getEntity() != null && ef.getEntity().getType() != null)
                log.debugf("take field '%s(%s)'(%s) as VARCHAR(%d)",
                           ef.getName(),
                           Lang.getTypeClass(ef.getType()).getName(),
                           ef.getEntity().getType().getName(),
                           Daos.DEFAULT_VARCHAR_WIDTH);
            ef.setColumnType(ColType.VARCHAR);
            ef.setWidth(Daos.DEFAULT_VARCHAR_WIDTH);
        }
    }

    public static FilePool getFilePool() {
        return conf.getPool();
    }

    public static void setFilePool(FilePool pool) {
        conf.setPool(pool);
    }

    public static void setCharacterStream(int index, Object obj, PreparedStatement stat) throws SQLException {
        try {
            File f = Jdbcs.getFilePool().createFile(".dat");
            Streams.writeAndClose(new FileWriter(f), (Reader)obj);
            stat.setCharacterStream(index, new FileReader(f), f.length());
        }
        catch (FileNotFoundException e) {
            throw Lang.impossible();
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
    }
    
    public static JdbcExpertConfigFile getConf() {
        return conf;
    }
}

class ReadOnceInputStream extends FilterInputStream implements Serializable {

	private static final long serialVersionUID = -2601685798106193691L;

	private File f;

	public boolean readed;

	protected ReadOnceInputStream(File f) throws FileNotFoundException {
		super(new FileInputStream(f));
		this.f = f;
	}

	public int read() throws IOException {
		readed = true;
		return super.read();
	}

	public int read(byte[] b) throws IOException {
		readed = true;
		return super.read(b);
	}

	public int read(byte[] b, int off, int len) throws IOException {
		readed = true;
		return super.read(b, off, len);
	}

	public void close() throws IOException {
		super.close();
		f.delete();
	}

	protected void finalize() throws Throwable {
		f.delete();
		super.finalize();
	}

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        Streams.writeAndClose(out, new FileInputStream(f));
    }
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException{
        f = Jdbcs.getFilePool().createFile(".dat");
        Files.write(f, in);
    }
}
