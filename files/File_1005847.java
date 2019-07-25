package cn.crap.service;

import cn.crap.adapter.Adapter;
import cn.crap.beans.Config;
import cn.crap.dao.custom.CustomProjectDao;
import cn.crap.dao.mybatis.ProjectDao;
import cn.crap.dto.ProjectDto;
import cn.crap.enu.LogType;
import cn.crap.enu.MyError;
import cn.crap.enu.SettingEnum;
import cn.crap.enu.TableId;
import cn.crap.framework.MyException;
import cn.crap.model.Log;
import cn.crap.model.Project;
import cn.crap.model.ProjectCriteria;
import cn.crap.query.ProjectQuery;
import cn.crap.service.tool.SettingCache;
import cn.crap.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectService extends BaseService<Project, ProjectDao> implements ILogConst, IConst {
    @Autowired
    private LogService logService;
    @Autowired
    private CustomProjectDao customMapper;
    @Autowired
    private SettingCache settingCache;

    private ProjectDao projectDao;
    @Resource
    public void ProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
        super.setBaseDao(projectDao, TableId.PROJECT);
    }

    /**
     * 添加
     *
     * @param project
     * @return
     */
    @Override
    public boolean insert(Project project) throws MyException{
        if (project == null) {
            return false;
        }
        if (MyString.isNotEmpty(project.getPassword())) {
            project.setPassword(MD5.encrytMD5(project.getPassword(), project.getId()));
        }

        if (MyString.isEmpty(project.getCover())){
            project.setCover(Tools.getAvatar());
        }
        return super.insert(project);
    }

    /**
     * 记录日志，�?更新
     * @param project
     */
    public boolean update(Project project, boolean needAddLog) throws MyException{
        if (needAddLog) {
            Project dbModel = super.getById(project.getId());
            Log log = Adapter.getLog(dbModel.getId(), L_PROJECT_CHINESE, dbModel.getName(), LogType.UPDATE, dbModel.getClass(), dbModel);
            logService.insert(log);
        }

        if (project.getPassword() != null) {
            if (project.getPassword().equals(C_DELETE_PASSWORD)) {
                project.setPassword("");
            } else {
                project.setPassword(MD5.encrytMD5(project.getPassword(), project.getId()));
            }
        }
        return super.update(project);
    }

    /**
     * 记录日志，�?删除
     *
     * @param id
     */
    @Override
    public boolean delete(String id) throws MyException{
        Assert.notNull(id);
        Project dbModel = super.getById(id);

        Log log = Adapter.getLog(dbModel.getId(), L_PROJECT_CHINESE, dbModel.getName(), LogType.DELTET, dbModel.getClass(), dbModel);
        logService.insert(log);

        return super.delete(id);
    }

    /**
     * 查询项目
     * @param query
     * @return
     * @throws MyException
     */
    public List<Project> query(ProjectQuery query) throws MyException {
        Assert.notNull(query);

        Page page = new Page(query);
        ProjectCriteria example = getProjectCriteria(query);
        example.setLimitStart(page.getStart());
        example.setMaxResults(page.getSize());
        example.setOrderByClause(query.getSort() == null ? TableField.SORT.SEQUENCE_DESC : query.getSort());

        return projectDao.selectByExample(example);
    }

    /**
     * 查询项目数�?
     * @param query
     * @return
     * @throws MyException
     */
    public int count(ProjectQuery query) throws MyException {
        Assert.notNull(query);

        ProjectCriteria example = getProjectCriteria(query);
        return projectDao.countByExample(example);
    }

    private ProjectCriteria getProjectCriteria(ProjectQuery query) throws MyException {
        ProjectCriteria example = new ProjectCriteria();
        ProjectCriteria.Criteria criteria = example.createCriteria();
        if (query.getName() != null) {
            criteria.andNameLike("%" + query.getName() + "%");
        }
        if (query.getStatus() != null) {
            criteria.andStatusEqualTo(query.getStatus());
        }
        if (query.getUserId() != null) {
            criteria.andUserIdEqualTo(query.getUserId());
        }
        return example;
    }

    /**
     * 根�?�用户ID查询所有该用户加入的项目�?创建的项目
     */
//    public List<String> queryMyProjectIdByUserId(String userId) {
//        Assert.notNull(userId, "userId can't be null");
//        return customMapper.queryProjectIdByUserId(userId);
//    }
    public List<Project> query(String userId, boolean onlyJoin, String name, Page page) {
        Assert.notNull(userId, "userId can't be null");
        return customMapper.queryProjectByUserId(userId, onlyJoin, name, page);
    }

    public int count(String userId, boolean onlyJoin, String name) {
        Assert.notNull(userId, "userId can't be null");
        return customMapper.countProjectByUserId(userId, onlyJoin, name);
    }


    /**
     * 获�?�邀请将入的链接
     * @param projectDto
     * @return
     */
    public String getInviteUrl(ProjectDto projectDto) throws MyException {
        Assert.notNull(projectDto);
        if (LoginUserHelper.getUser().getId().equals(projectDto.getUserId())) {
            return settingCache.getDomain() + "/user/projectUser/invite.do?code=" + Aes.encrypt(projectDto.getId() + SEPARATOR + System.currentTimeMillis());
        }
        return null;
    }

    /**
     * 解�?邀请�?
     * @param code
     * @return
     * @throws MyException
     */
    @Nullable
    public String getProjectIdFromInviteCode(String code) throws MyException{
        if (MyString.isEmpty(code)){
            throw new MyException(MyError.E000065, "邀请�?�?能为空");
        }

        try{
            String originalCode = Aes.desEncrypt(code);
            String projectId = originalCode.split(SEPARATOR)[0];
            String timeStr = originalCode.split(SEPARATOR)[1];
            if (Long.parseLong(timeStr) + TWO_HOUR > System.currentTimeMillis()){
                return projectId;
            }
            throw new MyException(MyError.E000065, "抱歉，�?�得太晚了，邀请�?过期");
        } catch (Exception e){
            throw new MyException(MyError.E000065, "未知异常：" + e.getMessage());
        }
    }


}
