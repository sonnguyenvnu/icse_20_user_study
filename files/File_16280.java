package org.hswebframework.web.database.manager.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hswebframework.web.Sqls;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.exception.AccessDenyException;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.hswebframework.web.database.manager.DatabaseManagerService;
import org.hswebframework.web.database.manager.SqlExecuteRequest;
import org.hswebframework.web.database.manager.SqlExecuteResult;
import org.hswebframework.web.database.manager.SqlInfo;
import org.hswebframework.web.database.manager.meta.ObjectMetadata;
import org.hswebframework.web.database.manager.sql.TransactionInfo;
import org.hswebframework.web.datasource.DataSourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/database/manager")
@Api(tags = "å¼€å?‘äººå‘˜å·¥å…·-æ•°æ?®åº“ç»´æŠ¤", value = "æ•°æ?®åº“ç»´æŠ¤")
@Authorize(permission = "database-manager", description = "æ•°æ?®åº“ç»´æŠ¤")
public class DataBaseManagerController {

    @Autowired
    private DatabaseManagerService databaseManagerService;

    @GetMapping("/metas")
    @Authorize(action = Permission.ACTION_QUERY, description = "èŽ·å?–å…ƒæ•°æ?®")
    @ApiOperation("èŽ·å?–æ•°æ?®åº“å…ƒæ•°æ?®")
    public ResponseMessage<Map<ObjectMetadata.ObjectType, List<? extends ObjectMetadata>>> parseAllObject() throws Exception {
        return parseAllObject(null);
    }

    @GetMapping("/metas/{datasourceId}")
    @Authorize(action = Permission.ACTION_QUERY, description = "èŽ·å?–å…ƒæ•°æ?®")
    @ApiOperation("èŽ·å?–æŒ‡å®šæ•°æ?®æº?çš„å…ƒæ•°æ?®")
    public ResponseMessage<Map<ObjectMetadata.ObjectType, List<? extends ObjectMetadata>>> parseAllObject(
            @PathVariable
            @ApiParam("æ•°æ?®æº?ID") String datasourceId) throws Exception {

        DataSourceHolder.switcher().use(datasourceId);
        return ResponseMessage.ok(databaseManagerService.getMetas());
    }

    @PostMapping(value = "/execute/{datasourceId}", consumes = MediaType.TEXT_PLAIN_VALUE)
    @Authorize(action = "execute", description = "æ‰§è¡ŒSQL")
    @ApiOperation(value = "æŒ‡å®šæ•°æ?®æº?æ‰§è¡ŒSQL")
    public ResponseMessage<List<SqlExecuteResult>> execute(
            @PathVariable @ApiParam("æ•°æ?®æº?ID") String datasourceId,
            @RequestBody @ApiParam("SQLè„šæœ¬") String sqlLines) throws Exception {
        DataSourceHolder.switcher().use(datasourceId);
        return ResponseMessage.ok(databaseManagerService.execute(SqlExecuteRequest.builder()
                .sql(parseSql(sqlLines, datasourceId))
                .build()));

    }

    @PostMapping(value = "/execute", consumes = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "æ‰§è¡ŒSQL")
    @Authorize(action = "execute", description = "æ‰§è¡ŒSQL")
    public ResponseMessage<List<SqlExecuteResult>> execute(@RequestBody
                                                           @ApiParam("SQLè„šæœ¬") String sqlLines) throws Exception {
        return ResponseMessage.ok(databaseManagerService
                .execute(SqlExecuteRequest.builder()
                        .sql(parseSql(sqlLines, null))
                        .build()));
    }

    @PostMapping(value = "/transactional/execute/{transactionalId}", consumes = MediaType.TEXT_PLAIN_VALUE)
    @Authorize(action = "execute", description = "æ‰§è¡ŒSQL")
    @ApiOperation(value = "å¼€å?¯äº‹åŠ¡æ‰§è¡ŒSQL")
    public ResponseMessage<List<SqlExecuteResult>> executeTransactional(@PathVariable @ApiParam("äº‹åŠ¡ID") String transactionalId,
                                                                        @ApiParam("SQLè„šæœ¬") @RequestBody String sqlLines) throws Exception {
        return ResponseMessage.ok(databaseManagerService.execute(transactionalId, SqlExecuteRequest.builder()
                .sql(parseSql(sqlLines, null))
                .build()));
    }

    @PostMapping(value = "/transactional/execute/{transactionalId}/{dataSourceId}", consumes = MediaType.TEXT_PLAIN_VALUE)
    @Authorize(action = "execute", description = "æ‰§è¡ŒSQL")
    @ApiOperation(value = "å¼€å?¯äº‹åŠ¡æ‰§è¡ŒæŒ‡å®šæ•°æ?®æº?å¯¹SQL")
    public ResponseMessage<List<SqlExecuteResult>> executeTransactional(@PathVariable @ApiParam("äº‹åŠ¡ID") String transactionalId,
                                                                        @PathVariable @ApiParam("æ•°æ?®æº?ID") String dataSourceId,
                                                                        @ApiParam("SQLè„šæœ¬") @RequestBody String sqlLines) throws Exception {
        DataSourceHolder.switcher().use(dataSourceId);
        return ResponseMessage.ok(databaseManagerService.execute(transactionalId, SqlExecuteRequest.builder()
                .sql(parseSql(sqlLines, dataSourceId))
                .build()));
    }

    @GetMapping("/transactional/new")
    @Authorize(action = "execute", description = "æ‰§è¡ŒSQL")
    @ApiOperation("æ–°å»ºäº‹åŠ¡")
    public ResponseMessage<String> newTransaction() throws Exception {
        return ResponseMessage.ok(databaseManagerService.newTransaction());
    }

    @GetMapping("/transactional/new/{dataSourceId}")
    @Authorize(action = "execute", description = "æ‰§è¡ŒSQL")
    @ApiOperation("æŒ‡å®šæ•°æ?®æº?æ–°å»ºäº‹åŠ¡")
    public ResponseMessage<String> newTransaction(@PathVariable String dataSourceId) throws Exception {
        DataSourceHolder.switcher().use(dataSourceId);
        return ResponseMessage.ok(databaseManagerService.newTransaction(dataSourceId));
    }


    @GetMapping("/transactional")
    @Authorize(action = "execute", description = "æ‰§è¡ŒSQL")
    @ApiOperation("èŽ·å?–å…¨éƒ¨äº‹åŠ¡ä¿¡æ?¯")
    public ResponseMessage<List<TransactionInfo>> allTransaction() throws Exception {
        return ResponseMessage.ok(databaseManagerService.allTransaction());
    }

    @PostMapping("/transactional/{id}/commit")
    @Authorize(action = "execute", description = "æ‰§è¡ŒSQL")
    @ApiOperation("æ??äº¤äº‹åŠ¡")
    public ResponseMessage<String> commitTransaction(@PathVariable String id) throws Exception {
        databaseManagerService.commit(id);
        return ResponseMessage.ok();
    }

    @PostMapping("/transactional/{id}/rollback")
    @Authorize(action = "execute", description = "æ‰§è¡ŒSQL")
    @ApiOperation("å›žæ»šäº‹åŠ¡")
    public ResponseMessage<String> rollbackTransaction(@PathVariable String id) throws Exception {
        databaseManagerService.rollback(id);
        return ResponseMessage.ok();
    }


    private List<SqlInfo> parseSql(String sqlText, String datasourceId) {
      //  Authentication authentication = Authentication.current().orElse(null);

        List<String> sqlList = Sqls.parse(sqlText);
        return sqlList.stream().map(sql -> {
            SqlInfo sqlInfo = new SqlInfo();
            sqlInfo.setSql(sql);
            sqlInfo.setDatasourceId(datasourceId);
            sqlInfo.setType(sql.split("[ ]")[0].toLowerCase());
//            if (authentication != null) {
//                if (!authentication.hasPermission("database-manager", sqlInfo.getType())) {
//
//                   // throw new AccessDenyException("æ?ƒé™?ä¸?è¶³");
//                }
//            }
            return sqlInfo;
        }).collect(Collectors.toList());
    }

}
