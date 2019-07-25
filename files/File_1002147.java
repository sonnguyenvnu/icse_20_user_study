package me.zhengjie.rest;

import cn.hutool.core.util.PageUtil;
import me.zhengjie.domain.vo.ColumnInfo;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.service.GenConfigService;
import me.zhengjie.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-01-02
 */
@RestController
@RequestMapping("api")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @Autowired
    private GenConfigService genConfigService;

    @Value("${generator.enabled}")
    private Boolean generatorEnabled;

    /**
     * æŸ¥è¯¢æ•°æ?®åº“å…ƒæ•°æ?®
     * @param name
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/generator/tables")
    public ResponseEntity getTables(@RequestParam(defaultValue = "") String name,
                                   @RequestParam(defaultValue = "0")Integer page,
                                   @RequestParam(defaultValue = "10")Integer size){
        int[] startEnd = PageUtil.transToStartEnd(page+1, size);
        return new ResponseEntity(generatorService.getTables(name,startEnd), HttpStatus.OK);
    }

    /**
     * æŸ¥è¯¢è¡¨å†…å…ƒæ•°æ?®
     * @param tableName
     * @return
     */
    @GetMapping(value = "/generator/columns")
    public ResponseEntity getTables(@RequestParam String tableName){
        return new ResponseEntity(generatorService.getColumns(tableName), HttpStatus.OK);
    }

    /**
     * ç”Ÿæˆ?ä»£ç ?
     * @param columnInfos
     * @return
     */
    @PostMapping(value = "/generator")
    public ResponseEntity generator(@RequestBody List<ColumnInfo> columnInfos, @RequestParam String tableName){
        if(!generatorEnabled){
            throw new BadRequestException("æ­¤çŽ¯å¢ƒä¸?å…?è®¸ç”Ÿæˆ?ä»£ç ?ï¼?");
        }
        generatorService.generator(columnInfos,genConfigService.find(),tableName);
        return new ResponseEntity(HttpStatus.OK);
    }
}
