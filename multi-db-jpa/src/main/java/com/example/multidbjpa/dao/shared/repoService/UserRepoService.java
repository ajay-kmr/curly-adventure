package com.example.multidbjpa.dao.shared.repoService;

import com.example.multidbjpa.dao.shared.entity.User;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

@Service
@CommonsLog
public abstract class UserRepoService extends BaseRepoService<User, Long> {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Transactional(readOnly = true)
    public User findByEmailId(String emailId) throws Exception {

        Criteria criteria = getCriteriaToFindUserByEmailId(emailId);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<User> users = (List<User>) criteria.list();
        if (CollectionUtils.isEmpty(users)) {
            return null;
        } else if (users.size() > 1) {
//            throw new UsernameNotFoundException("Multiple user found with emailId:- " + emailId);
            throw new Exception("Multiple user found with emailId:- " + emailId);
        }
        return users.get(0);
    }

    private Criteria getCriteriaToFindUserByEmailId(String emailId) {
        return getCriteria()
                .setFetchMode("roles", FetchMode.JOIN)
                .add(eq("emailId", emailId))
                .add(eq("deleted", false))
                .add(eq("accountExpired", false))
                .add(eq("accountLocked", false))
                .add(eq("credentialsExpired", false))
                .add(eq("accountEnabled", true));
    }
}
