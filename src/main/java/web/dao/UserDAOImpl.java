package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User save(User user) {
        if (user.getId()==null){
            em.persist(user);
        }
        else {
            em.merge(user);
        }
        return user;
    }

    @Override
    public User getUserbyId(Long id) {
        TypedQuery<User> tp = em.createQuery("select u from User u where u.id = :id", User.class);
        tp.setParameter("id", id);

        return tp.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void delete(Long id) {
        em.remove(getUserbyId(id));
    }

}
