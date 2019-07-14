package com.alibaba.excel.metadata;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Excel基础模型
 * @author jipengfei
 */
public class BaseRowModel {

    /**
     * �?列样�?
     */
    private Map<Integer,CellStyle> cellStyleMap = new HashMap<Integer,CellStyle>();

    public void addStyle(Integer row, CellStyle cellStyle){
        cellStyleMap.put(row,cellStyle);
    }

    public CellStyle getStyle(Integer row){
        return cellStyleMap.get(row);
    }

    public Map<Integer, CellStyle> getCellStyleMap() {
        return cellStyleMap;
    }

    public void setCellStyleMap(Map<Integer, CellStyle> cellStyleMap) {
        this.cellStyleMap = cellStyleMap;
    }
}
