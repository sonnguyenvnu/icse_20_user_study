package org.hswebframework.web.organizational.authorization;

import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.organizational.authorization.relation.Relations;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 人员�?��?信�?�,用于获�?�当�?登录用户对应的人员相关信�?�
 *
 * @author zhouhao
 * @see Authentication
 * @since 3.0
 */
public interface PersonnelAuthentication extends Serializable {

    /**
     * 获�?�当�?登录人员信�?�
     *
     * @return 人员�?��?信�?�
     * @see Optional
     * @see Authentication#getAttribute(String)
     */
    static Optional<PersonnelAuthentication> current() {
        return Optional.ofNullable(PersonnelAuthenticationHolder.get());
    }

    /**
     * @return 人员的基本信�?�
     */
    Personnel getPersonnel();

    /**
     * 获�?�人员的关系信�?�
     * <pre>
     *     boolean isLeader = PersonnelAuthentication
     *     .current().get()
     *     .getRelations()
     *     // 和张三的人员为leader关系, 我是张三的leader
     *     .has("leader","人员","张三");
     *     //我是开�?�部的leader
     *     //.has("leader","部门","开�?�部");
     *     //�??转关系: 张三是我的leader
     *     //.has("leader","人员","张三","PRE");
     * </pre>
     * <pre>
     *     List<Relation> relations= PersonnelAuthentication.current()
     *     //查找用户关系
     *     .map(PersonnelAuthentication::getRelations)
     *     .map(relations -> relations.findAll("leader"))
     *     .orElse(null)
     * </pre>
     *
     * @return 人员关系信�?�
     * @see Relations
     * @see org.hswebframework.web.organizational.authorization.relation.Relation
     */
    Relations getRelations();

    /**
     * @return 人员所在行政区域ID, �?�返回根节点, 永远�?会返回{@code null}
     */
    Set<TreeNode<String>> getDistrictIds();

    /**
     * @return 人员所在机构ID, �?�返回根节点, 永远�?会返回{@code null}
     */
    Set<TreeNode<String>> getOrgIds();

    /**
     * @return 人员�?�务ID, �?�返回根节点, 永远�?会返回{@code null}
     */
    Set<TreeNode<String>> getPositionIds();

    /**
     * @return 人员所在部门ID, �?�返回根节点, 永远�?会返回{@code null}
     */
    Set<TreeNode<String>> getDepartmentIds();

    /**
     * 获�?�人员的所有�?��?信�?�
     *
     * @return �?��?信�?�
     */
    Set<Position> getPositions();

    default Optional<Position> getPosition(String id) {
        return getPositions().stream().filter(position -> position.getId().equals(id)).findFirst();
    }

    /**
     * @return 根地区ID
     */
    default Set<String> getRootDistrictId() {
        return getDistrictIds().stream().map(TreeNode::getValue).collect(Collectors.toSet());
    }

    /**
     * @return 根机构ID
     */
    default Set<String> getRootOrgId() {
        return getOrgIds().stream().map(TreeNode::getValue).collect(Collectors.toSet());
    }

    /**
     * @return 根�?��?ID
     */
    default Set<String> getRootPositionId() {
        return getPositionIds().stream().map(TreeNode::getValue).collect(Collectors.toSet());
    }

    /**
     * @return 根部门ID
     */
    default Set<String> getRootDepartmentId() {
        return getDepartmentIds().stream().map(TreeNode::getValue).collect(Collectors.toSet());
    }

    /**
     * @return 所有地区ID
     */
    default Set<String> getAllDistrictId() {
        return getDistrictIds().stream().map(TreeNode::getAllValue).flatMap(List::stream).collect(Collectors.toSet());
    }

    /**
     * @return 所有机构ID
     */
    default Set<String> getAllOrgId() {
        return getOrgIds().stream().map(TreeNode::getAllValue).flatMap(List::stream).collect(Collectors.toSet());
    }

    /**
     * @return 所有�?��?ID
     */
    default Set<String> getAllPositionId() {
        return getPositionIds().stream().map(TreeNode::getAllValue).flatMap(List::stream).collect(Collectors.toSet());
    }

    /**
     * @return 所有部门ID
     */
    default Set<String> getAllDepartmentId() {
        return getDepartmentIds().stream().map(TreeNode::getAllValue).flatMap(List::stream).collect(Collectors.toSet());
    }
}
