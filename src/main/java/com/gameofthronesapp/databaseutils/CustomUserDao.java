package com.gameofthronesapp.databaseutils;


import com.gameofthronesapp.datamodel.CustomUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomUserDao {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SessionFactory sessionFactory = CustomUserSessionFactory.getCustomUserSessionFactory();

    public void saveUser(CustomUser customUser) {
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
        s.merge(customUser);
        t.commit();
        s.close();
    }

    private boolean userExists(CustomUser user) {
        return findUserByEmail(user.getEmail()) != null;
    }

    public CustomUser findUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CustomUser> userQuery = cb.createQuery(CustomUser.class);
        Root<CustomUser> root = userQuery.from(CustomUser.class);
        userQuery.select(root).where(cb.equal(root.get("email"), email));
        CustomUser user = session.createQuery(userQuery).getSingleResultOrNull();
        return user;
    }
}
