package com.sohu.cache.util;

import static com.sohu.cache.constant.EmptyObjectConstant.EMPTY_STRING;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sohu.cache.constant.BaseConstant;
import com.sohu.cache.constant.EmptyObjectConstant;

/**
 * Description: String Utils
 * 
 * @author nileader / nileader@gmail.com
 * @Date Feb 10, 2012
 */
public class StringUtil {

    /**
     * Check String is equals. targetStr compare with compareStrArray, and
     * return true if equals one or more
     * 
     * @param targetStr
     *            寰呮瘮�?�冪殑瀛楃�?涓�
     * @param compareStrArray
     *            瑕佹瘮�?�冪殑涓�釜鎴栧涓�?�瓧绗︿覆�??囧噯
     * @return
     */
    public static boolean containsIgnoreCase( final String originalStr, final CharSequence targetStr ) {

        if ( null == originalStr ) {
            return false;
        }

        String originalStrCaps = originalStr.toUpperCase();
        String targetStrCaps = targetStr.toString().toUpperCase();
        return originalStrCaps.contains( targetStrCaps );
    }


    /**
     * Description: Remove {_, -, @, $, #, /, &} in string and make letter after
     * this uppercase.<br>
     * e.g. ni_lea-der@gmail./com -> niLeaDerGmail.Com
     * 
     * @param @param inputString
     * @param @param firstCharacterUppercase The first letter is need uppercase.
     * @return String
     * @throws
     */
    public static String convertToCamelCaseString( String inputString, boolean firstCharacterUppercase ) {
        if ( null == inputString ) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for ( int i = 0; i < inputString.length(); i++ ) {
            char c = inputString.charAt( i );

            switch ( c ) {
            case '_':
            case '-':
            case '@':
            case '$':
            case '#':
            case ' ':
            case '/':
            case '&':
                if ( sb.length() > 0 ) {
                    nextUpperCase = true;
                }
                break;

            default:
                if ( nextUpperCase ) {
                    sb.append( Character.toUpperCase( c ) );
                    nextUpperCase = false;
                } else {
                    sb.append( c );
                }
                break;
            }
        }

        if ( firstCharacterUppercase ) {
            sb.setCharAt( 0, Character.toUpperCase( sb.charAt( 0 ) ) );
        } else {
            sb.setCharAt( 0, Character.toLowerCase( sb.charAt( 0 ) ) );
        }

        return sb.toString();
    }

    /**
     * Return Default if originalStr is empty.
     * 
     * @param originalStr
     *            寰呯‘�?ゅ�
     * @param defaultStr
     *            榛樿�?��
     * @return 濡傛�?�originalStr涓虹┖锛岄�?�涔�?氨�?�斿洖defaultStr
     */
    public static String defaultIfBlank( String originalStr, String defaultStr ) {
        if ( StringUtil.isBlank( originalStr ) ) {
            return defaultStr;
        }
        Collections.emptyList();
        return originalStr;
    }

    /**
     * Check String is equals Ignore Case. targetStr compare with
     * compareStrArray, and return true if equals all
     * 
     * @param targetStr
     *            寰呮瘮�?�冪殑瀛楃�?涓�
     * @param compareStrArray
     *            瑕佹瘮�?�冪殑涓�釜鎴栧涓�?�瓧绗︿覆�??囧噯
     * @return true if targetStr same with every string in compareStrArray
     */
    public static boolean equalsIgnoreCaseAll( String targetStr, String... compareStrArray ) {

        if ( StringUtil.isBlank( targetStr ) || null == compareStrArray || 0 == compareStrArray.length ) {
            return false;
        }
        for ( int i = 0; i < compareStrArray.length; i++ ) {
            if ( !targetStr.equalsIgnoreCase( compareStrArray[i] ) ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check String is equals. targetStr compare with compareStrArray, and
     * return true if equals one or more
     * 
     * @param targetStr
     *            寰呮瘮�?�冪殑瀛楃�?涓�
     * @param compareStrArray
     *            瑕佹瘮�?�冪殑涓�釜鎴栧涓�?�瓧绗︿覆�??囧噯
     * @return true if targetStr same with string in compareStrArray one at
     *         least
     */
    public static boolean equalsIgnoreCaseOne( String targetStr, String... compareStrArray ) {

        if ( StringUtil.isBlank( targetStr ) || null == compareStrArray || 0 == compareStrArray.length ) {
            return false;
        }
        for ( int i = 0; i < compareStrArray.length; i++ ) {
            if ( targetStr.equalsIgnoreCase( compareStrArray[i] ) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 閫氳繃�?ｅ垯�?�ㄨ�?��?�瑰紡锛屾壘�?�轰竴涓�?�瓧绗︿覆涓�?�?�夋寚瀹氱殑瀛愪覆
     * 
     * @param @param originalStr 瀛楃�?涓�
     * @param @param regex 寰呮煡鎵惧瓙涓茬殑�?ｅ垯�?�ㄨ�?�寮�
     * @return List<String> 瀛愪覆闆嗗悎
     * 
     *         <pre>
     *  瀵�/1.1.1.1:sid=0x2337c7074dofj02e,37775[1](queued=0,recved=6,sent=7,sid=0x2337c7074f1102e,sdlfjle,dsfe�?�勭粨�?�滄槸锛�
     * [sid=0x2337c7074dofj02e, , sid=0x2337c7074f1102e, ]
     * </pre>
     */
    public static List< String > findAllByRegex( String originalStr, String regex ) {

        if ( StringUtil.isBlank( originalStr ) || StringUtil.isBlank( regex ) )
            return null;

        List< String > targetStrList = new ArrayList< String >();
        final Pattern patternOfTargetStr = Pattern.compile( regex, Pattern.CANON_EQ );
        final Matcher matcherOfTargetStr = patternOfTargetStr.matcher( originalStr );
        /** 寮�瑙ｆ瀽 */
        while ( matcherOfTargetStr.find() ) {
            targetStrList.add( StringUtil.trimToEmpty( matcherOfTargetStr.group() ) );
        }
        return targetStrList;
    }

    /**
     * 閫氳繃�?ｅ垯�?�ㄨ�?��?�瑰紡锛屾壘�?�轰竴涓�?�瓧绗︿覆涓涓�釜鎸囧畾�?�勫瓙涓�
     * 
     * @param @param originalStr 瀛楃�?涓�
     * @param @param regex 寰呮煡鎵惧瓙涓茬殑�?ｅ垯�?�ㄨ�?�寮�
     * @return List<String> 瀛愪覆闆嗗悎
     * 
     *         <pre>
     *  瀵�/1.1.1.1:sid=0x2337c7074dofj02e,37775[1](queued=0,recved=6,sent=7,sid=0x2337c7074f1102e,sdlfjle,dsfe�?�勭粨�?�滄槸锛�
     * sid=0x2337c7074dofj02e,
     * </pre>
     */
    public static String findFirstByRegex( String originalStr, String regex ) {

        if ( StringUtil.isBlank( originalStr ) || StringUtil.isBlank( regex ) )
            return EMPTY_STRING;

        final Pattern patternOfTargetStr = Pattern.compile( regex, Pattern.CANON_EQ );
        final Matcher matcherOfTargetStr = patternOfTargetStr.matcher( originalStr );
        /** 寮�瑙ｆ瀽 */
        if ( matcherOfTargetStr.find() ) {
            return StringUtil.trimToEmpty( matcherOfTargetStr.group() );
        }
        return EMPTY_STRING;
    }

    /**
     * �?�熸垚绌虹櫧�?��
     * 
     * @param lines
     *            �?�屾暟
     */
    public static String generateLineBlank( int lines ) {
        StringBuilder sb = new StringBuilder();

        for ( int i = 0; i < lines; i++ ) {
            sb.append( "\n" );
        }

        return sb.toString();
    }

    /**
     * make first letter lower case for str
     * 
     * @return Same letter, but the first letter is lower case.
     */
    public static String makeFirstLetterLowerCase( String str ) {
        String firstLetter = str.substring( 0, 1 );
        return firstLetter.toLowerCase() + str.substring( 1, str.length() );
    }

    /***
     * check if orginalStr is null or empty. <br>
     * If have more than one originalStr, use isBlank(String...
     * originalStrArray)
     * 
     * @param originalStr
     *            寰呯‘�?ゅ�
     * @return true or false;
     */
    public static boolean isBlank( String originalStr ) {
        if ( null == originalStr ) {
            return true;
        }
        if ( originalStr.contains( BaseConstant.WORD_SEPARATOR ) ) {
            return false;
        }
        return trimToEmpty( originalStr ).isEmpty();
    }

    /***
     * check if orginalStr is null or empty
     * 
     * @param String
     *            ... originalStrArray
     * @return true if have one blank at least.
     */
    public static boolean isBlank( String... originalStrArray ) {

        if ( null == originalStrArray || 0 == originalStrArray.length )
            return true;
        for ( int i = 0; i < originalStrArray.length; i++ ) {
            if ( isBlank( originalStrArray[i] ) )
                return true;
        }
        return false;
    }

    /**
     * check the originalStr is contain the whitespace
     * 
     * @param originalStr
     * @return true if contain whitespace
     */
    public static boolean isContainWhitespace( String originalStr ) {

        if ( StringUtil.isBlank( originalStr ) ) {
            return true;
        }
        int strLen = originalStr.length();
        for ( int i = 0; i < strLen; i++ ) {
            char ch = originalStr.charAt( i );
            if ( Character.isWhitespace( ch ) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串连接，使用指定分隔符
     * 
     * @param subStr
     * @return
     */
    public static String join( String... subStrs ) {

        if ( null == subStrs || 0 == subStrs.length ) {
            return EMPTY_STRING;
        }
        StringBuilder sb = new StringBuilder();
        for ( String subStr : subStrs ) {
            sb.append( subStr ).append( BaseConstant.WORD_SEPARATOR );
        }
        String sbStr = sb.toString();
        if ( sbStr.endsWith( BaseConstant.WORD_SEPARATOR ) ) {
            sbStr = StringUtil.replaceLast( sbStr, BaseConstant.WORD_SEPARATOR, "" );
        }
        return sbStr;
    }

    /**
     * Description: Replaces last substring of this string that matches the
     * given regular expression with the given replacement.<br>
     * Do not worry about null pointer
     * 
     * @param @param regex
     * @param @param replacement
     * @return String
     * @throws
     */
    public static String replaceAll( String originalStr, String replacement, String regex ) {
        return StringUtil.trimToEmpty( originalStr ).replaceAll( regex, replacement );
    }

    public static String replaceAll( String originalStr, String replacement, String... regexArray ) {

        if ( 0 == regexArray.length )
            return originalStr;

        for ( String regex : regexArray ) {
            originalStr = StringUtil.replaceAll( originalStr, replacement, regex );
        }

        return originalStr;
    }

    /**
     * Description: Replaces last substring of this string that matches the
     * given regular expression with the given replacement.
     * 
     * @param @param regex
     * @param @param replacement
     * @param @return
     * @return String
     * @throws
     */
    public static String replaceLast( String originalStr, String regex, String replacement ) {

        if ( StringUtil.isBlank( originalStr ) )
            return EMPTY_STRING;

        int index = originalStr.lastIndexOf( regex );
        if ( -1 == index )
            return originalStr;

        // �??�?瓨�?�ㄨ繖涓猧ndex涔嬪墠�?�勬�?�?�塻tr
        String temp = originalStr.substring( 0, index );
        String temp2 = originalStr.substring( index, originalStr.length() );

        temp2 = temp2.replaceFirst( regex, replacement );

        originalStr = temp + temp2;

        return originalStr;
    }

    
    /**
     * Description: Replaces all {n} placeholder use params
     * 
     * @param originalStr
     *            a string such as :
     *            "select * from table where id={0}, name={1}, gender={3}"
     * @param replacementParams
     *            real params: 1,yinshi.nc,male
     * @note n start with 0
     */
    public static String replaceSequenced( String originalStr, Object... replacementParams ) {

        if ( StringUtil.isBlank( originalStr ) )
            return EMPTY_STRING;
        if ( null == replacementParams || 0 == replacementParams.length )
            return originalStr;

        for ( int i = 0; i < replacementParams.length; i++ ) {
            String elementOfParams = replacementParams[i] + EmptyObjectConstant.EMPTY_STRING;
            if ( StringUtil.trimToEmpty( elementOfParams ).equalsIgnoreCase( "null" ) )
                elementOfParams = EmptyObjectConstant.EMPTY_STRING;
            originalStr = originalStr.replace( "{" + i + "}", StringUtil.trimToEmpty( elementOfParams ) );
        }

        return originalStr;
    }

    /**
     * �?剧疆�?�嶇紑锛屽�?�滆繖涓�?�瓧绗︿覆宸茬粡�?��?繖涓�?�墠缂�簡锛岄�?�涔�?氨涓嶄綔浠讳�?鎿嶄綔銆� TODO none test
     * */
    public static String setPrefix( String originalStr, String prefix ) {
        originalStr = StringUtil.trimToEmpty( originalStr );
        prefix = StringUtil.trimToEmpty( prefix );
        if ( !originalStr.startsWith( prefix ) ) {
            originalStr = prefix + originalStr;
        }
        return originalStr;
    }

    /**

    /**
     * �?�ゆ柇瀛楃�?涓�?�槸�?��?秴�?�囨寚瀹氶暱�?�︼�?濡備�?瓒呰繃锛屾�?��?�犳寚瀹氬悗缂�
     * 
     * @param originalStr
     *            "閾舵椂�?��
     * @param maxLength
     *            2
     * @param suffix
     *            ...
     * @return "閾舵椂..."
     */
    public static String subStringIfTooLong( String originalStr, int maxLength, String suffix ) {
        if ( StringUtil.isBlank( originalStr ) )
            return EmptyObjectConstant.EMPTY_STRING;
        if ( maxLength < 0 )
            maxLength = 0;
        if ( originalStr.length() > maxLength )
            return originalStr.substring( 0, maxLength ) + StringUtil.trimToEmpty( suffix );
        return originalStr;
    }

    /**
     * Returns a copy of the string, with leading and trailing whitespace
     * omitted. Don't worry the NullPointerException. Will never return Null.
     * 
     * @param originalStr
     * @return "" or String without empty str.
     */
    public static String trimToEmpty( String originalStr ) {
        if ( null == originalStr || originalStr.isEmpty() )
            return EMPTY_STRING;
        if ( originalStr.equals( BaseConstant.WORD_SEPARATOR ) )
            return originalStr;
        return originalStr.trim();
    }

    /**
     * URL编�?
     * 
     * @param s
     *            String to be translated.
     * @param enc
     *            The name of a supported character encoding.
     * @return
     */
    public static String urlEncode( String s, String enc ) {
        if ( StringUtil.isBlank( s ) )
            return StringUtil.trimToEmpty( s );
        try {
            return java.net.URLEncoder.encode( trimToEmpty( s ), enc );
        } catch ( UnsupportedEncodingException e ) {
            return s;
        }
    }

    /**
     * URL编�?,使用UTF-8编�?
     * 
     * @param s
     *            String to be translated.
     * @param enc
     *            The name of a supported character encoding.
     * @return
     */
    public static String urlEncode( String s ) {
        if ( StringUtil.isBlank( s ) )
            return StringUtil.trimToEmpty( s );
        return urlEncode( trimToEmpty( s ), "UTF-8" );
    }

}
