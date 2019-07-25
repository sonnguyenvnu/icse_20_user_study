package cn.crap.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SerializeUtil implements Serializable{
	private static final long serialVersionUID = 2625611887089949567L;
	private static Log logger = LogFactory.getLog(SerializeUtil.class);// æ—¥å¿—ç±»
    /**
     * åº?åˆ—åŒ–
     * 
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
    	if(object==null){
    		return null;
    	}
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // åº?åˆ—åŒ–
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * å??åº?åˆ—åŒ–
     * 
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
    	if(bytes==null){
    		return null;
    	}
    	ByteArrayInputStream bais = null;
        try {
            // å??åº?åˆ—åŒ–
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("å??åº?åˆ—åŒ–å¤±è´¥",e);
        }
        return null;
    }

}
